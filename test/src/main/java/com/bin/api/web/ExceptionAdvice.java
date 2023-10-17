package com.bin.api.web;


import com.bin.webase.core.query.TestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@ControllerAdvice
@ResponseBody
@CrossOrigin
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Object handleHttpMessageNotReadableException(HttpServletResponse httpServletResponse,
                                                        HttpMessageNotReadableException e) {
        String accessControl = "Access-Control-Allow-Origin";
        if (!httpServletResponse.getHeaderNames().contains(accessControl)) {
            httpServletResponse.setHeader(accessControl, "*");
        }
        return wrapperException(e);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object handleHttpRequestMethodNotSupportedException(HttpServletResponse httpServletResponse,
                                                               HttpRequestMethodNotSupportedException e) {
        String accessControl = "Access-Control-Allow-Origin";
        if (!httpServletResponse.getHeaderNames().contains(accessControl)) {
            httpServletResponse.setHeader(accessControl, "*");
        }
        return wrapperException(e);
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.OK)
    public Object noHandlerFoundException(HttpServletResponse httpServletResponse, NoHandlerFoundException e) {
        String accessControl = "Access-Control-Allow-Origin";
        if (!httpServletResponse.getHeaderNames().contains(accessControl)) {
            httpServletResponse.setHeader(accessControl, "*");
        }
        return wrapperException(e);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public Object handHttpMediaTypeNotAcceptableException(HttpServletResponse httpServletResponse, HttpMediaTypeNotAcceptableException e) {
        String accessControl = "Access-Control-Allow-Origin";
        if (!httpServletResponse.getHeaderNames().contains(accessControl)) {
            httpServletResponse.setHeader(accessControl, "*");
        }
        return wrapperException(e);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Object handleHttpMediaTypeNotSupportedException(HttpServletRequest request,HttpServletResponse httpServletResponse,
                                                           Exception e) {
        String accessControl = "Access-Control-Allow-Origin";
        if (!httpServletResponse.getHeaderNames().contains(accessControl)) {
            httpServletResponse.setHeader(accessControl, "*");
        }
        return wrapperException(e);
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Throwable.class)
    public Object handleException(HttpServletResponse httpServletResponse, Throwable e) {
        String accessControl = "Access-Control-Allow-Origin";
        if (!httpServletResponse.getHeaderNames().contains(accessControl)) {
            httpServletResponse.setHeader(accessControl, "*");
        }
        return wrapperException(e);
    }

    private Object wrapperException(Throwable e) {
        TestException result = ExceptionWrapper.getJsonExceptionWrapper2(e);
        return result;
    }
}
