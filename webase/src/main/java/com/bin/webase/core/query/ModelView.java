package com.bin.webase.core.query;

import com.bin.webase.core.context.IRepository;

public abstract class ModelView<T> {

    protected T root;

    public ModelView() {
    }

    public T getRoot() {
        return root;
    }

    public boolean isRootNull() {
        return root == null;
    }

    public ModelView(Long id, IRepository<T> repository) {
        this.root = repository.getModel(id);
    }

    public ModelView(T root) {
        this.root = root;
    }
}
