package com.bin.webase.core.web;

import com.bin.webase.exception.ApplicationException;
import com.bin.webase.exception.ErrorCode;


public class ThreadWebContextHolder {
    private static final ThreadLocal<WebContext> THREAD_LOCAL = new ThreadLocal<>();

    public static WebContext getContext() {
        if (THREAD_LOCAL.get() != null) {
            return THREAD_LOCAL.get();
        } else {
            throw new ApplicationException(ErrorCode.NoFunctionID);
        }
    }

    public static void setContext(WebContext value) {
        THREAD_LOCAL.set(value);
    }

    public static void removeContext() {
        WebContext session = THREAD_LOCAL.get();
        if (null != session) {
            THREAD_LOCAL.remove();
        }
    }

}
