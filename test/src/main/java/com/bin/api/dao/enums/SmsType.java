package com.bin.api.dao.enums;

public enum SmsType {
    forget("forget_"),
    active("active_"),
    ;

    private final String key;

    SmsType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
