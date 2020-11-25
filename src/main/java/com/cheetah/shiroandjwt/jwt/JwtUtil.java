package com.cheetah.shiroandjwt.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;


/**
 * @ClassName: JwtUtil
 * @Description: jwt工具类
 * @Date: 2020/9/25
 * @Author: zyq
 * @Version: 1.0
 */
public class JwtUtil {

    //JWT-account
    public static final String ACCOUNT = "username";
    //JWT-currentTimeMillis
    public final static String CURRENT_TIME_MILLIS = "currentTimeMillis";
    //有效期时间2小时
    public static final long EXPIRE_TIME = 2 * 60 * 60 * 1000L;

    public static final String SECRET_KEY = "shirokey";


    /**
     * 生成签名
     *
     * @param account
     * @param currentTimeMillis
     * @return
     */
    public static String sign(String account, String currentTimeMillis) {
        // 帐号加JWT私钥加密
        String secret = account + SECRET_KEY;
        // 此处过期时间，单位：毫秒
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withClaim(ACCOUNT, account)
                .withClaim(CURRENT_TIME_MILLIS, currentTimeMillis)
                .withExpiresAt(date)
                //创建一个新的JWT，并使用给定的算法进行标记
                .sign(algorithm);
    }

    /**
     * 校验token是否正确
     *
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        String secret = getClaim(token, ACCOUNT) + SECRET_KEY;
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        verifier.verify(token);
        return true;
    }

    /**
     * 获得Token中的信息无需secret解密也能获得
     *
     * @param token
     * @param claim
     * @return
     */
    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

}

