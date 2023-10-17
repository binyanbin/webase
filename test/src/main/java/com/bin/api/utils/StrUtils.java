package com.bin.api.utils;

import java.util.Random;

public class StrUtils {

    private static final String MIX = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String NUMBER = "0123456789";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";

    public static String generateMixString(int length) {
        return generateRandomString(MIX, length);
    }

    public static String generateUpperString(int length) {
        return generateRandomString(UPPER, length);
    }

    public static String generateLowerString(int length) {
        return generateRandomString(LOWER, length);
    }

    public static String generateNumberString(int length) {
        return generateRandomString(NUMBER, length);
    }


    private static String generateRandomString(String characters, int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
}
