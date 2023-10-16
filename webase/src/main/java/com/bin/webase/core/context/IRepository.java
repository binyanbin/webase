package com.bin.webase.core.context;

public interface IRepository<T> {

    T getModel(Long id);

    void add(T model);

    void delete(Long id);

    void update(T model);

    Long getMaxId();

    String getTableName();
}
