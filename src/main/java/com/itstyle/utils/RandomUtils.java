package com.itstyle.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class RandomUtils {
    public static String getRandomStringNumbers(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    public static String getVerCode(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    public static String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String get36UUID() {
        return UUID.randomUUID().toString();
    }

    public static String getChar(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String getToken() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    public static String getUserid() {
        String userid = getToken().replaceAll("-", "");
        userid = DigestUtils.md5Hex(userid);
        System.out.println(userid);
        userid = userid.replaceAll("a", "0").replaceAll("b", "1").replaceAll("c", "2").replaceAll("d", "3")
                .replaceAll("e", "4")
                .replaceAll("f", "5");
        return "USER" + userid.substring(0, 16);
    }

    public static void main(String[] args) {
        System.out.println(getUserid());
    }
}