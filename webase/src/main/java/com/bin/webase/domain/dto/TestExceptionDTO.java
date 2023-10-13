package com.bin.webase.domain.dto;

public class TestExceptionDTO extends ErrorDTO {
    private String stack;

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }
}
