package com.bin.webase.core.model;

import com.bin.webase.core.web.ApiToken;
import com.bin.webase.core.web.ThreadWebContextHolder;
import com.bin.webase.core.web.WebContext;
import com.bin.webase.exception.ErrorCheck;
import com.bin.webase.exception.ErrorCode;

import java.util.Date;
import java.util.List;

public abstract class FunctionObject {

    public <T extends ApiToken> T getToken(Class<T> clazz) {
        WebContext webContext = ThreadWebContextHolder.getContext();
        ApiToken token = webContext.getToken();
        if (token != null) {
            return (T) token;
        } else {
            return null;
        }
    }

    protected Date getTime() {
        return ThreadWebContextHolder.getContext().getBeginTime();
    }

    protected ApiToken getToken() {
        return ThreadWebContextHolder.getContext().getToken();
    }

    protected void validateFunction() {
        List<FunctionId> functionIds = getFunction();
        if (functionIds != null && functionIds.size() > 0) {
            ErrorCheck.check(FunctionId.validate(getToken(), functionIds), ErrorCode.NoFunctionID);
        }
    }

    protected List<FunctionId> getFunction() {
        return null;
    }
}
