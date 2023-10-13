package com.bin.webase.domain.bqo;

import com.bin.webase.domain.unitwork.CommitResult;
import com.bin.webase.exception.ErrorCode;

import java.math.BigDecimal;
import java.util.List;

public class BQOUtils {
    public static NoResultBQO success() {
        return new NoResultBQO(0);
    }

    public static BQO success(boolean result) {
        return new BQO<>(result);
    }

    public static BQO success(long result) {
        return new BQO<>(result);
    }

    public static BQO success(String result) {
        return new BQO<>(result);
    }

    public static BQO success(List<String> result) {
        return new BQO<>(result);
    }

    public static BQO success(BigDecimal result) {
        return new BQO<>(result);
    }

    public static NoResultBQO response(CommitResult commitResult) {
        if (commitResult.isSuccess()) {
            return success();
        } else {
            if (commitResult.getMsg() == null || commitResult.getMsg().length() == 0) {
                return fail(ErrorCode.UnitWorkCommitFail);
            } else {
                return fail(ErrorCode.UnitWorkCommitFail, commitResult.getMsg());
            }
        }
    }

    public static ErrorBQO fail(ErrorCode errorCode, String msg) {
        return new ErrorBQO(errorCode.getCode(), msg);
    }

    public static ErrorBQO fail(ErrorCode errorCode) {
        return new ErrorBQO(errorCode.getCode(), errorCode.getReasoning());
    }

    public static <T> BQO<T> success(T data) {
        return new BQO<T>(data);
    }
}
