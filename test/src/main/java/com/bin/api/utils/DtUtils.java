package com.bin.api.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DtUtils {
    public static String toDayString(Date date) {
        if (date != null) {
            DateFormat DAY_STRING = new SimpleDateFormat("yyyy-MM-dd");
            return DAY_STRING.format(date);
        } else {
            return null;
        }
    }
}
