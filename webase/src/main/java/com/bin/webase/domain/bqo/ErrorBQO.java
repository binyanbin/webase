package com.bin.webase.domain.bqo;

public class ErrorBQO extends NoResultBQO {

    private String msg;

    public ErrorBQO() {
    }

    public ErrorBQO(Integer code, String msg) {
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
