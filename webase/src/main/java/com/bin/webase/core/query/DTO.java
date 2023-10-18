package com.bin.webase.core.query;

public class DTO<T> extends NoResult {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DTO(T data) {
        super();
        this.data = data;
    }
}
