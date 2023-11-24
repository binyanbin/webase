package com.bin.webase.exception;

import com.bin.webase.core.context.WebaseContext;

import java.util.Collection;

public class ErrorCheck {

    public static void check(boolean result, ErrorCode errorCode, String msg) {
        if (!result) {
            throw new ApplicationException(errorCode, msg);
        }
    }

    public static void check(boolean result, ErrorCode errorCode) {
        if (!result) {
            throw new ApplicationException(errorCode);
        }
    }

    public static void checkArgument(boolean result, String msg) {
        if (!result) {
            throw new ApplicationException(ErrorCode.ArgumentsIncorrect, msg);
        }
    }

    public static void checkNotNull(Object obj, String msg) {
        if (obj == null) {
            throw new ApplicationException(ErrorCode.NullPointerException, msg);
        }
    }

    public static void checkNotNull(Object obj, ErrorCode errorCode) {
        if (obj == null) {
            throw new ApplicationException(errorCode);
        }
    }

    public static void checkCollectionNotEmpty(Collection collection, String msg) {
        if (collection == null || collection.size() == 0) {
            throw new ApplicationException(ErrorCode.NullPointerException, msg);
        }
    }

    public static void checkServerBusy(boolean result) {
        if (!result) {
            throw new ApplicationException(ErrorCode.ServerBusy);
        }
    }

    public static void checkDuplicateOperate(String key) {
        if (WebaseContext.getCacheBean().hasKey(key)) {
            throw new ApplicationException(ErrorCode.QuickOperate);
        } else {
            WebaseContext.getCacheBean().set(key, "", 2);
        }
    }

    public static void checkNotNullException(Object obj, String msg) throws Exception {
        if (obj == null) {
            throw new Exception(msg);
        }
    }

    public static void checkException(boolean result, String msg) throws Exception {
        if (!result) {
            throw new Exception(msg);
        }
    }
}
