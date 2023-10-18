package com.bin.webase.core.query;


import java.io.Serializable;

/**
 * 无结果返回
 */
public class NoResult implements Serializable {
    private Integer code;

    NoResult() {
        this.code = 0;
    }

    NoResult(Integer code) {
        this.code = code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
