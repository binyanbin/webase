package com.bin.webase.domain.web;


public interface IDisposeRequest {

    WebContext createSession(boolean session);

    void frequentAccess(WebContext webContext);

    void quickSubmit(WebContext webContext);

    void validSign(WebContext webContext);
}
