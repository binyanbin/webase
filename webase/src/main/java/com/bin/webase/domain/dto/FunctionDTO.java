package com.bin.webase.domain.dto;

import com.bin.webase.domain.content.FunctionObject;


public abstract class FunctionDTO<T> extends FunctionObject {

    private T data;
    private final Integer code;

    public FunctionDTO() {
        super();
        this.code = 0;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }
}
