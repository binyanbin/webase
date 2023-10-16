package com.bin.webase.domain.query;

public class Error extends NoResult {

    private String msg;

    public Error() {
    }

    public Error(Integer code, String msg) {
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
