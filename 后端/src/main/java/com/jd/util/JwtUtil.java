package com.jd.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * 生成令牌jwt的工具类
 */
public class JwtUtil {
    // 令牌密匙
    private static String signKey = "xiaowulaile";
    // 令牌过期时间
    private static Long time = 13 * 60 * 60 * 1000L;

    /**
     * 生成jwt令牌
     *
     * @param claims JWT第二部分负载payload中存储的内容
     * @return
     */
    public static String getJWT(Map<String, Object> claims) {
        // 生成加密后的jwt字符串
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .compact();
        return jwt;
    }

    /**
     * 解析jwt令牌
     *
     * @param jwt jwt令牌
     * @return JWT第二部分负载payload中存储的内容
     */
    public static Claims parseJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
