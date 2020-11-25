package com.cheetah.shiroandjwt.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @ClassName: JwtToken
 * @Description: 封装jwtToken
 * @Date: 2020/11/24
 * @Author: cheetah
 * @Version: 1.0
 */
public class JwtToken implements AuthenticationToken {

    private final String token;

    public JwtToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
