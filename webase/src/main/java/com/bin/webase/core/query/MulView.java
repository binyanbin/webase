package com.bin.webase.core.query;


import java.util.List;

public abstract class MulView<Q, T> {
    protected List<T> list;
    protected Q condition;

    public List<T> getList() {
        return list;
    }

    protected Q getCondition() {
        return condition;
    }

    public boolean isListNull() {
        return list == null || list.isEmpty();
    }

    public MulView(Q condition) {
        this.condition = condition;
    }
}
