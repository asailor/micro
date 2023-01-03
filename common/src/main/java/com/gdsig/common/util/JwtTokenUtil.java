package com.gdsig.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 *
 * @author xs
 * @date 2022/11/28上午 10:19
 */

public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    /**
     * 根据负责生成JWT的token
     */
    private static String generateToken(Map<String, Object> claims, String secret, long exp) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate(exp))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private static Claims getClaimsFromToken(String token, String secret) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     */
    private static Date generateExpirationDate(long exp) {
        return new Date(System.currentTimeMillis() + exp * 1000);
    }

    /**
     * 从token中获取登录用户名
     */
    public static String getUserNameFromToken(String token, String secret) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token, secret);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否还有效
     *
     * @param token    客户端传入的token
     * @param username 从数据库中查询出来的用户信息
     */
    public static boolean validateToken(String token, String username, String secret) {
        String tokenUsername = getUserNameFromToken(token, secret);
        return tokenUsername.equals(username) && !isTokenExpired(token, secret);
    }

    /**
     * 判断token是否已经失效
     */
    private static boolean isTokenExpired(String token, String secret) {
        Date expiredDate = getExpiredDateFromToken(token, secret);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private static Date getExpiredDateFromToken(String token, String secret) {
        Claims claims = getClaimsFromToken(token, secret);
        return claims.getExpiration();
    }

    /**
     * 根据用户信息生成token
     */
    public static String generateToken(String username, String secret, long exp) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, username);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims, secret, exp);
    }

}
