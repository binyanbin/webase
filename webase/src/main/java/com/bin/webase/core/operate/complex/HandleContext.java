package com.bin.webase.core.operate.complex;

import com.bin.webase.core.model.IParam;

public  class HandleContext<T> {
    private IParam param;
    private T result;

    public IParam getParam() {
        return param;
    }

    public void setParam(IParam param) {
        this.param = param;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
