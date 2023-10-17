package com.bin.webase.core.query;

import com.bin.webase.core.entity.FunctionId;
import com.bin.webase.core.operate.IParam;
import com.bin.webase.core.web.ApiToken;
import com.bin.webase.core.web.ThreadWebContextHolder;
import com.bin.webase.core.web.WebContext;
import com.bin.webase.exception.ErrorCheck;
import com.bin.webase.exception.ErrorCode;

import java.util.List;


public class Query<T> {

    private T data;
    private final Integer code;

    public Query() {
        validate();
        this.code = 0;
    }

    public Query(IParam param) {
        param.validate();
        validate();
        this.code = 0;
    }

    public Query(T data) {
        this.code = 0;
        setData(data);
    }

    public T getData() {
        return data;
    }

    protected void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }


    private ApiToken token;
    private List<FunctionId> functionIds;

    protected void validate() {
        functionIds = getFunctionId();
        if (functionIds != null && functionIds.size() > 0) {
            WebContext webContext = ThreadWebContextHolder.getContext();
            token = webContext.getToken();
            ErrorCheck.check(FunctionId.validate(token, functionIds), ErrorCode.NoFunctionID);
        }
    }

    protected ApiToken getToken() {
        return token;
    }

    protected <T> T getToken(Class<T> clazz) {
        return (T) token;
    }

    protected List<FunctionId> getFunctionId() {
        return null;
    }
}
