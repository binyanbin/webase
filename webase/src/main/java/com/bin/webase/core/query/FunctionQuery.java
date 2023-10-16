package com.bin.webase.core.query;

import com.bin.webase.core.entity.FunctionObject;


public abstract class FunctionQuery<T> extends FunctionObject {

    private T data;
    private final Integer code;

    public FunctionQuery() {
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
