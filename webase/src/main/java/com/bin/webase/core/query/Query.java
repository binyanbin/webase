package com.bin.webase.core.query;


import com.bin.webase.core.operate.IParam;

/**
 * 业务查询对象
 */
public class Query<T> extends NoResult {

    private T data;

    public Query(IParam param) {
        super(0);
        param.validate();
    }

    public Query() {
        super(0);
    }

    public Query(T result) {
        super(0);
        setData(result);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
