package com.bin.webase.core.query;

import com.bin.webase.core.model.FunctionObject;
import com.bin.webase.core.model.IParam;
import com.bin.webase.core.model.NoParam;


public abstract class Query<T, P extends IParam> extends FunctionObject {

    public T execute(P p) {
        validateFunction();
        p.validate();
        return getData(p);
    }

    public T execute() {
        return execute((P) NoParam.NO_PARAM);
    }

    protected abstract T getData(P p);


}
