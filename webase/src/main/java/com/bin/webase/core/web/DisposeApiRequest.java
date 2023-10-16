package com.bin.webase.core.web;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;


@Aspect
public class DisposeApiRequest {

    private IDisposeRequest dispose;
    private boolean validSign;

    public DisposeApiRequest(boolean validSign, IDisposeRequest disposeRequest) {
        this.dispose = disposeRequest;
        this.validSign = validSign;
    }

    @Before("@annotation(com.bin.webase.core.web.ApiMethodAttribute)")
    public void construct(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        ApiMethodAttribute methodAttribute = methodSignature.getMethod().getAnnotation(ApiMethodAttribute.class);

        boolean validSession = !methodAttribute.nonSessionValidation();

        WebContext webContext = dispose.createSession(validSession);
        ThreadWebContextHolder.setContext(webContext);
        String businessType = methodAttribute.businessType();
        webContext.setBusinessType(businessType);
        dispose.frequentAccess(webContext);
        dispose.quickSubmit(webContext);
        if (validSign) {
            dispose.validSign(webContext);
        }
    }
}
