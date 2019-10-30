package com.itstyle.common.redis;

public class RedisSmsManager {
    private static final String USER_SMS_KEY = "app:sms:";
    private static final String USER_SMS_LOGIN_KEY = "login:";
    private static final String KEY = "app:sms:login:";

    public static String getVerCodeLogin(String phoneNum) {
        return RedisManager.get("app:sms:login:" + phoneNum);
    }

    public static String setLoginVerCode(String phoneNum, String verificationCode) {
        return RedisManager.set("app:sms:login:" + phoneNum, verificationCode, 600);
    }
}