package com.itstyle.common.redis;

import com.itstyle.common.jwt.JWTUtils;
import org.apache.commons.lang3.StringUtils;

public class RedisTokenManager {
    public static final String USER_TOKEN_KEY = "app:user:token";

    public static String getToken(String userid) {
        return RedisManager.hget("app:user:token", userid);
    }

    public static String setToken(String userid) {
        String token = JWTUtils.createTokenExpiration(userid, 2592000000L);

        RedisManager.hset("app:user:token", userid, token);
        return token;
    }

    public static void delUserToken(String userId) {
        RedisManager.hdel("app:user:token", userId);
    }

    public static boolean checkToken(String userId, String token) {
        String daniuToken = getToken(userId);
        if ((StringUtils.isNotBlank(token)) && (token.equals(daniuToken))) {
            return true;
        }

        return false;
    }
}