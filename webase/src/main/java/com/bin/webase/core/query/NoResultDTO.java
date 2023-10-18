package com.bin.webase.core.query;


import java.io.Serializable;

/**
 * 无结果返回
 */
public class NoResultDTO implements Serializable {
    private Integer code;

    NoResultDTO() {
        this.code = 0;
    }

    NoResultDTO(Integer code) {
        this.code = code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
