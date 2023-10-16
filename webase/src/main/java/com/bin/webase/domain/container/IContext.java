package com.bin.webase.domain.container;

public interface IContext {

    <T> T getBean(Class<T> clazz);

}
