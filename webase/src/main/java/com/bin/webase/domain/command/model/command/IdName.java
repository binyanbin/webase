package com.bin.webase.domain.command.model.command;

public class IdName<T> {
    private final T id;
    private final String name;

    public IdName(T id, String name) {
        this.id = id;
        this.name = name;
    }

    public T getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
