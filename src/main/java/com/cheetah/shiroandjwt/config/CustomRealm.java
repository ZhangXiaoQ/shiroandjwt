package com.cheetah.shiroandjwt.config;

import com.cheetah.shiroandjwt.common.CommonResultStatus;
import com.cheetah.shiroandjwt.common.HttpStatus;
import com.cheetah.shiroandjwt.common.ResultStatus;
import com.cheetah.shiroandjwt.entity.SysUserInfo;
import com.cheetah.shiroandjwt.exception.BusinessException;
import com.cheetah.shiroandjwt.mapper.SysUserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: CustomRealm
 * @Description: 自定义Realm进行权限认证和身份认证
 * @Date: 2020/11/21
 * @Author: cheetah
 * @Version: 1.0
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private SysUserInfoMapper userInfoMapper;

    /**
     *  权限认证/获取授权信息
     *  该方法只有在需要权限认证时才会进入，
     *  比如前面配置类中配置了
     *  filterChainDefinitionMap.put("/admin/**", "roles[admin]"); 的管理员角色，
     *  这时进入 /admin 时就会进入该方法来检查权限
     * @author cheetah
     * @date 2020/11/21
     * @param principals:
     * @return: org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("————权限认证————");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        String role = userInfoMapper.getRole(username);
        Set<String> set = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        set.add(role);
        //设置该用户拥有的角色
        info.setRoles(set);
        return info;
    }

    /**
     *  身份认证/获取身份验证信息
     *  Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *  该方法则是需要身份认证时（比如前面的 Subject.login() 方法）才会进入
     * @author cheetah
     * @date 2020/11/21
     * @param authenticationToken:  用户身份信息 token
     * @return: org.apache.shiro.authc.AuthenticationInfo
     * 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("————身份认证方法————");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 从数据库获取对应用户名密码的用户
        SysUserInfo userInfo = userInfoMapper.getUserByLogin(token.getUsername());
        if (null == userInfo) {
            throw new BusinessException(CommonResultStatus.USERNAME_ERROR);
        } else if (!userInfo.getPassword().equals(new String((char[]) token.getCredentials()))) {
            throw new BusinessException(CommonResultStatus.PASSWORD_ERROR);
        }
        return new SimpleAuthenticationInfo(token.getPrincipal(), userInfo.getPassword(), getName());
    }
}
