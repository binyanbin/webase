package com.bin.api.web.base;

import com.bin.webase.core.context.IContext;
import org.springframework.context.ApplicationContext;

public class ContextImp implements IContext {

    private  ApplicationContext applicationContext;

    public ContextImp(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    public <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

}
