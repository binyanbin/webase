package com.bin.webase.domain.query;

public class TestException extends Error {
    private String stack;

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }
}
