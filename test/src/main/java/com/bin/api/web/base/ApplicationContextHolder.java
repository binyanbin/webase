package com.bin.api.web.base;

import com.bin.webase.core.context.IContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    private static ContextImp springContextImp;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
        ApplicationContextHolder.springContextImp = new ContextImp(applicationContext);
    }

    public static <T> T getBean(Class<T> clazz) {
        return springContextImp.getBean(clazz);
    }


    public static String getEnvironmentProperty(String key) {
        return applicationContext.getEnvironment().getProperty(key);
    }

    public static IContext getSpringContext() {
        return springContextImp;
    }
}




