package com.bin.webase.domain.bqo;



import java.io.Serializable;

/**
 * 无结果返回
 */
public class NoResultBQO implements Serializable {
    private Integer code;

    NoResultBQO(){

    }

    NoResultBQO(Integer code) {
        this.code = code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
