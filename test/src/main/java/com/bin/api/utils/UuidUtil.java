package com.bin.api.utils;

import java.util.UUID;

/**
 * @author yanbin
 * @date 2017/7/1
 */
public final class UuidUtil {
    public static String newUuidString() {
        UUID uuid = UUID.randomUUID();
        String result = Long.toHexString(uuid.getMostSignificantBits())
                + Long.toHexString(uuid.getLeastSignificantBits());
        if (result.length() < 32) {
            int len = 32 - result.length();
            for (int i = 0; i < len; i++) {
                result = result + "0";
            }
        }
        return result;
    }
}
