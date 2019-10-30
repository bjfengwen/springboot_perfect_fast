package com.itstyle.common.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtils.class);

    private static SecretKeySpec SECRET_KEY = new SecretKeySpec("locateSecretKeyToJwt".getBytes(), SignatureAlgorithm.HS256.getJcaName());

    public static String createTokenExpiration(String userid, long expiresTime) {
        HashMap map = new HashMap();

        map.put("userid", userid);

        String jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(map)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setExpiration(new Date(
                        System.currentTimeMillis() + expiresTime))
                .compact();

        return "Bearer " + jwt;
    }

    public static String createToken(String userid) {
        HashMap map = new HashMap();

        map.put("userid", userid);

        String jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(map)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        return "Bearer " + jwt;
    }

    public static Map<String, Object> validateToken(String token) {
        try {
            return (Map) Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token
                            .replace("Bearer ", ""))
                    .getBody();
        } catch (Exception e) {
            LOGGER.warn("JWT令牌验证不通过：{} ", e.getMessage());
        }
        return null;
    }
}