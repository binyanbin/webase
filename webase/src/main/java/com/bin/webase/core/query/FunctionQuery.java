package com.bin.webase.core.query;

import com.bin.webase.core.entity.FunctionId;
import com.bin.webase.core.operate.IParam;
import com.bin.webase.core.web.ApiToken;
import com.bin.webase.core.web.ThreadWebContextHolder;
import com.bin.webase.core.web.WebContext;
import com.bin.webase.exception.ErrorCheck;
import com.bin.webase.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;


public abstract class FunctionQuery<T> {

    private T data;
    private final Integer code;

    public FunctionQuery() {
        super();
        validate();
        this.code = 0;
    }

    public FunctionQuery(IParam param) {
        super();
        param.validate();
        validate();
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


    private ApiToken token;
    private List<FunctionId> functionIds;

    protected void validate() {
        functionIds = new ArrayList<>();
        initFunction();
        if (functionIds.size() > 0) {
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

    protected void addFunction(FunctionId functionId) {
        functionIds.add(functionId);
    }

    public void initFunction() {

    }
}
