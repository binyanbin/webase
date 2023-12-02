package com.bin.webase.core.operate;

import com.bin.webase.core.operate.complex.HandleContext;

public abstract class Handle extends BaseOperate {
    private Handle nextHandler;

    public void setNextHandler(Handle nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void doHandle(HandleContext payContext) {
        handle(payContext);
        if (nextHandler != null) {
            nextHandler.doHandle(payContext);
        }
    }

    public abstract void handle(HandleContext context);
}
