package com.bin.webase.core.query;

import com.bin.webase.core.context.IRepository;

public abstract class ModelView<T> {

    protected T model;

    public ModelView() {
    }

    public T getModel() {
        return model;
    }

    public boolean isRootNull() {
        return model == null;
    }

    public ModelView(Long id, IRepository<T> repository) {
        this.model = repository.getModel(id);
    }

    public ModelView(T model) {
        this.model = model;
    }
}
