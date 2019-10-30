package com.itstyle.common.check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckParams {
    private static final int PHONE_LENGTH = 11;
    private static Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");

    public static boolean isPhone(String phoneNum) {
        String regex = "^((13[0-9])|(14[0-9])|(15([0-9]))|(16([0-9]))|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$";
        if (phoneNum.length() != 11) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNum);
        boolean isMatch = matcher.matches();
        if (isMatch) {
            return isMatch;
        }
        return false;
    }

    public static boolean isVerificationNum(String number, Integer numberLength) {
        if (number.length() != numberLength.intValue()) {
            return false;
        }

        return isInteger(number);
    }

    public static boolean isInteger(String number) {
        return pattern.matcher(number).matches();
    }
}