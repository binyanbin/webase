package com.bin.webase.core.query;

public class ErrorDTO extends NoResult {

    private String msg;

    public ErrorDTO() {
    }

    public ErrorDTO(Integer code, String msg) {
        super(code);
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
