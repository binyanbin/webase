package com.bin.webase.core.context;

public interface IContext {

    <T> T getBean(Class<T> clazz);

}
