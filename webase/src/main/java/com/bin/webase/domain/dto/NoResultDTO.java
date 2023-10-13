package com.bin.webase.domain.dto;



import java.io.Serializable;

/**
 * 无结果返回
 */
public class NoResultDTO implements Serializable {
    private Integer code;

    NoResultDTO(){

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
