package com.bin.webase.core.operate;

import com.bin.webase.core.operate.complex.HandleContext;

public abstract class Handle extends BaseOperate {
    public abstract void handle(HandleContext context);
}
