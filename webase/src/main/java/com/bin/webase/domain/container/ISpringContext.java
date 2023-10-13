package com.bin.webase.domain.container;

public interface ISpringContext {

    <T> T getBean(Class<T> clazz);

}
