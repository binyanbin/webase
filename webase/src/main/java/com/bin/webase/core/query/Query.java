package com.bin.webase.core.query;

import com.bin.webase.core.context.IQueryLog;
import com.bin.webase.core.context.WebaseContext;
import com.bin.webase.core.model.FunctionObject;
import com.bin.webase.core.model.IParam;
import com.bin.webase.core.model.NoParam;
import com.bin.webase.core.model.QueryId;


public abstract class Query<T, P extends IParam> extends FunctionObject {

    public T execute(P p) {
        validateFunction();
        p.validate();
        IQueryLog queryLog = WebaseContext.getQueryLog();
        if (queryLog != null) {
            if (getQueryId() != null) {
                queryLog.log(getQueryId(), p);
            }
        }
        return getData(p);
    }

    public T execute() {
        return execute((P) NoParam.NO_PARAM);
    }

    protected abstract T getData(P p);

    public QueryId getQueryId() {
        return null;
    }


}
