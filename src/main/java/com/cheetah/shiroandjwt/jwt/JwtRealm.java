package com.cheetah.shiroandjwt.jwt;

import com.cheetah.shiroandjwt.common.CommonResultStatus;
import com.cheetah.shiroandjwt.exception.BusinessException;
import com.cheetah.shiroandjwt.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: JwtRealm
 * @Description: 自定义的身份验证与权限验证
 * @Date: 2020/11/24
 * @Author: zyq
 * @Version: 1.0
 */
@Slf4j
public class JwtRealm extends AuthorizingRealm {


    @Autowired
    private UserInfoService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     *  身份验证
     * @author cheetah
     * @date 2020/11/25
     * @param token:
     * @return: org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String credentials = (String) token.getCredentials();
        String username = null;
        try {
            boolean verify = JwtUtil.verify(credentials);
            if (!verify) {
                throw new AuthenticationException("Token校验不正确");
            }
            username = JwtUtil.getClaim(credentials, JwtUtil.ACCOUNT);
        } catch (Exception e) {
            throw new BusinessException(CommonResultStatus.TOKEN_CHECK_ERROR,e.getMessage());
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，不设置则使用默认的SimpleCredentialsMatcher
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username, //用户名
                credentials, //凭证
                getName()  //realm name
        );
        return authenticationInfo;
    }

    /**
     *  权限校验
     * @author cheetah
     * @date 2020/11/25
     * @param principals:
     * @return: org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        String username = principals.toString();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //角色权限暂时不加
//        authorizationInfo.setRoles(userService.getRoles(username));
//        authorizationInfo.setStringPermissions(userService.queryPermissions(username));
        return authorizationInfo;
    }

}
