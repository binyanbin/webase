package com.bin.webase.core.query;

import com.bin.webase.core.context.IRepository;

public abstract class View<T> {

    protected T root;

    public View() {
    }

    public T getRoot() {
        return root;
    }

    public boolean isRootNull() {
        return root == null;
    }

    public View(Long id, IRepository<T> repository) {
        this.root = repository.getModel(id);
    }

    public View(T root) {
        this.root = root;
    }
}