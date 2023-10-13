package com.bin.webase.domain.bqo;

import com.bin.webase.domain.entity.FunctionObject;


public abstract class FunctionBQO<T> extends FunctionObject {

    private T data;
    private final Integer code;

    public FunctionBQO() {
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
