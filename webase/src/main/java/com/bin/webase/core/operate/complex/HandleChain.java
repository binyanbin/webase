package com.bin.webase.core.operate.complex;

import com.bin.webase.core.operate.Handle;

import java.util.ArrayList;
import java.util.List;

public abstract class HandleChain {
    private List<Handle> handles;

    public HandleChain() {
        this.handles = new ArrayList<>();
        init();
    }

    public void doHandle(HandleContext context) {
        if (handles.size() > 0) {
            for (Handle handle : handles) {
                handle.handle(context);
            }
        }
    }

    protected void addHandle(Handle handle) {
        handles.add(handle);
    }

    protected abstract void init();
}
