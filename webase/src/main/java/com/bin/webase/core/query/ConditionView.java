package com.bin.webase.core.query;


import java.util.List;

public abstract class ConditionView<Q, T> {
    protected List<T> root;
    protected Q condition;

    public List<T> getRoot() {
        return root;
    }

    protected Q getCondition() {
        return condition;
    }

    public boolean isRootNull() {
        return root == null || root.isEmpty();
    }

    public ConditionView() {
    }


    public ConditionView(Q condition) {
        this.condition = condition;
    }
}
