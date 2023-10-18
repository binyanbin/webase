package com.bin.webase.core.query;

import com.bin.webase.exception.ErrorCode;

import java.math.BigDecimal;
import java.util.List;

public class DTOUtils {
    public static NoResult success() {
        return new NoResult(0);
    }

    public static DTO<Boolean> success(boolean result) {
        return new DTO<>(result);
    }

    public static DTO<Long> success(long result) {
        return new DTO<>(result);
    }

    public static DTO<String> success(String result) {
        return new DTO<>(result);
    }

    public static DTO<List<String>> success(List<String> result) {
        return new DTO<>(result);
    }

    public static DTO<BigDecimal> success(BigDecimal result) {
        return new DTO<>(result);
    }

    public static ErrorDTO fail(ErrorCode errorCode, String msg) {
        return new ErrorDTO(errorCode.getCode(), msg);
    }

    public static ErrorDTO fail(ErrorCode errorCode) {
        return new ErrorDTO(errorCode.getCode(), errorCode.getReasoning());
    }

    public static <T> DTO<T> success(T data) {
        return new DTO<T>(data);
    }
}
