package com.bin.webase.domain.query;

import com.bin.webase.exception.ErrorCode;

import java.math.BigDecimal;
import java.util.List;

public class QueryUtils {
    public static NoResult success() {
        return new NoResult(0);
    }

    public static Query success(boolean result) {
        return new Query<>(result);
    }

    public static Query success(long result) {
        return new Query<>(result);
    }

    public static Query success(String result) {
        return new Query<>(result);
    }

    public static Query success(List<String> result) {
        return new Query<>(result);
    }

    public static Query success(BigDecimal result) {
        return new Query<>(result);
    }

    public static Error fail(ErrorCode errorCode, String msg) {
        return new Error(errorCode.getCode(), msg);
    }

    public static Error fail(ErrorCode errorCode) {
        return new Error(errorCode.getCode(), errorCode.getReasoning());
    }

    public static <T> Query<T> success(T data) {
        return new Query<T>(data);
    }
}
