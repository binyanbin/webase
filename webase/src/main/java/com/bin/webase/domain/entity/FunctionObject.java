package com.bin.webase.domain.entity;

import com.bin.webase.domain.web.ApiToken;
import com.bin.webase.domain.web.ThreadWebContextHolder;
import com.bin.webase.domain.web.WebContext;
import com.bin.webase.exception.ApplicationException;
import com.bin.webase.exception.ErrorCheck;
import com.bin.webase.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限
 */
public abstract class FunctionObject {
    private ApiToken token;
    private final List<FunctionId> functionIds;

    public FunctionObject() {
        functionIds = new ArrayList<>();
        initFunction();
        if (functionIds.size() > 0) {
            WebContext webContext = ThreadWebContextHolder.getContext();
            if (webContext != null) {
                token = webContext.getToken();
                if (token != null) {
                    boolean haveFunction = false;
                    for (FunctionId functionId : functionIds) {
                        haveFunction = token.validFunction(functionId);
                        if (haveFunction) {
                            break;
                        }
                    }
                    ErrorCheck.check(haveFunction, ErrorCode.NoFunctionID);
                } else {
                    throw new ApplicationException(ErrorCode.NoFunctionID);
                }
            } else {
                throw new ApplicationException(ErrorCode.NoFunctionID);
            }
        }
    }

    public ApiToken getToken() {
        return token;
    }

    public <T> T getToken(Class<T> clazz) {
        return (T) token;
    }

    public void setToken(ApiToken token) {
        this.token = token;
    }

    protected void addFunction(FunctionId functionId) {
        functionIds.add(functionId);
    }

    public void initFunction() {

    }
}
