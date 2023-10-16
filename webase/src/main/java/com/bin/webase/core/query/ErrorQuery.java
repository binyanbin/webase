package com.bin.webase.core.query;

public class ErrorQuery extends NoResult {

    private String msg;

    public ErrorQuery() {
    }

    public ErrorQuery(Integer code, String msg) {
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
