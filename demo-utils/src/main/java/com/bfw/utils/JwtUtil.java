package com.bfw.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;


public class JwtUtil {

    /**
     * @description: 生成JWT令牌
     * @author: 风花雪月
     * @date: 2024/8/31 15:25
     * @param: [secretKey, TTL, claims]
     * @return: String
     **/
    public static String createJWT(String secretKey, long TTL, Map<String, Object> claims) {
        // 设置jwt的body
        JwtBuilder builder = Jwts.builder()
                // 设置声明
                .setClaims(claims)
                // 设置签名算法和签名使用的秘钥
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + TTL));
        return builder.compact();
    }

    /**
     * @description: 解析JWT令牌
     * @author: 风花雪月
     * @date: 2024/8/31 15:26
     * @param: [secretKey, token]
     * @return: Claims
     **/
    public static Claims parseJWT(String secretKey, String token) {
        // 得到DefaultJwtParser
        Claims claims = Jwts.parser()
                // 设置签名的秘钥
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                // 设置需要解析的jwt
                .parseClaimsJws(token).getBody();
        return claims;
    }

}
