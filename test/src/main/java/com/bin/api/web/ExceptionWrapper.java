package com.bin.api.web;


import com.bin.webase.core.query.ErrorDTO;
import com.bin.webase.core.query.TestExceptionDTO;
import com.bin.webase.exception.ApplicationException;
import com.bin.webase.exception.ErrorCode;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author yanbin
 */

public class ExceptionWrapper {

    public static ErrorDTO getJsonExceptionWrapper(Throwable ex) {
        ErrorDTO result = new ErrorDTO();
        mapDTO(ex, result);
        return result;
    }

    public static TestExceptionDTO getJsonExceptionWrapper2(Throwable ex) {
        TestExceptionDTO result = new TestExceptionDTO();
        mapDTO(ex, result);
        result.setStack(Arrays.toString(ex.getStackTrace()));
        return result;
    }

    private static void mapDTO(Throwable ex, ErrorDTO result) {
        if (ex instanceof ApplicationException) {
            ApplicationException gex = (ApplicationException) ex;
            result.setCode(gex.getCode());
            result.setMsg(getMessage(ex, gex.getReasoning()));
        } else if (ex instanceof IllegalArgumentException) {
            result.setCode(ErrorCode.ArgumentsIncorrect.getCode());
            result.setMsg(getMessage(ex, ErrorCode.ArgumentsIncorrect.getReasoning()));
        } else if (ex instanceof IllegalStateException) {
            result.setCode(ErrorCode.IllegalStateException.getCode());
            result.setMsg(getMessage(ex, ErrorCode.IllegalStateException.getReasoning()));
        } else if (ex instanceof IndexOutOfBoundsException) {
            result.setCode(ErrorCode.UnKnowException.getCode());
            result.setMsg(getMessage(ex, ErrorCode.UnKnowException.getReasoning()));
        } else if (ex instanceof MissingServletRequestParameterException
                || ex instanceof HttpMessageNotReadableException
                || ex instanceof HttpRequestMethodNotSupportedException
                || ex instanceof HttpMediaTypeNotSupportedException
                || ex instanceof MethodArgumentTypeMismatchException) {
            result.setCode(ErrorCode.InterfaceContent.getCode());
            result.setMsg(ErrorCode.InterfaceContent.getReasoning());
        } else if (ex instanceof NullPointerException) {
            result.setCode(ErrorCode.NullPointerException.getCode());
            result.setMsg(getMessage(ex, ErrorCode.NullPointerException.getReasoning()));
        } else if (ex instanceof IOException) {
            result.setCode(ErrorCode.IOException.getCode());
            result.setMsg(getMessage(ex, ErrorCode.IOException.getReasoning()));
        } else if (ex instanceof NoHandlerFoundException) {
            result.setCode(ErrorCode.NoHandlerFound.getCode());
            result.setMsg(getMessage(ex, ErrorCode.NoHandlerFound.getReasoning()));
        } else {
            result.setCode(ErrorCode.UnKnowException.getCode());
            result.setMsg(getMessage(ex, ErrorCode.UnKnowException.getReasoning()));
        }
    }

    private static String getMessage(Throwable e, String msg) {
        if (StringUtils.hasText(e.getMessage())) {
            return e.getMessage();
        } else {
            return msg;
        }
    }

}
