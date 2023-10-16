package com.bin.webase.core.query;


import java.util.List;

public abstract class QueryView<Q, T> {
    protected List<T> root;
    protected Q query;

    public List<T> getRoot() {
        return root;
    }

    public boolean isRootNull() {
        return root == null || root.isEmpty();
    }

    public QueryView(Q query) {
        this.query = query;
    }
}
