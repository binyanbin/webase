package com.bin.webase.core.operate;

public enum ResultState {
    success(0),
    warn(1),
    fail(2),
    ;

    Integer id;

    ResultState(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
