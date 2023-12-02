package com.bin.webase.core.operate.complex;

import com.bin.webase.core.operate.Handle;

public abstract class HandleChain {
    private Handle firstHandle;
    private Handle endHandle;

    public HandleChain() {
        init();
    }

    public void doHandle(HandleContext context) {
        if (firstHandle != null) {
            firstHandle.doHandle(context);
        }
    }

    protected void addHandle(Handle handle) {
        if (firstHandle == null) {
            firstHandle = handle;
            endHandle = handle;
        } else {
            endHandle.setNextHandler(handle);
            endHandle = handle;
        }
    }

    protected abstract void init();
}
