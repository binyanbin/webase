package com.bin.webase.domain.content;

import com.bin.webase.domain.container.function.Function;
import com.bin.webase.exception.ErrorCheck;
import com.bin.webase.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

public abstract class FunctionObject {
    private ApiToken token;
    private final List<Function> functions;

    public FunctionObject() {
        token = ThreadWebContextHolder.getContext().getToken();
        functions = new ArrayList<>();
        initFunction();
        boolean haveFunction = false;
        if (functions != null && functions.size() > 0) {
            ErrorCheck.checkNotNull(token, ErrorCode.LoginError);
            for (Function function : functions) {
                haveFunction = token.validFunction(function);
                if (haveFunction) {
                    break;
                }
            }
            ErrorCheck.check(haveFunction, ErrorCode.NoFunctionID);
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

    protected void addFunction(Function function) {
        functions.add(function);
    }

    public void initFunction() {

    }
}
