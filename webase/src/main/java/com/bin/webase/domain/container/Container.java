package com.bin.webase.domain.container;


import com.bin.webase.exception.ErrorCheck;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Container {

    private final static Map<String, Object> beanContainer = new HashMap<>();
    private static ISequence sequence;
    private static ICache cache;
    private static IBranchLog branchLog;
    private static IContext springContext;
    private static StringBuilder errorMsg;


    public static void error(String msg) {
        if (errorMsg == null) {
            errorMsg = new StringBuilder().append(msg);
        } else {
            errorMsg.append(msg);
        }
    }

    public static void put(Object bean) {
        beanContainer.put(bean.getClass().getName(), bean);
    }

    public static <T extends IRepository> T getRepository(Class<T> clazz) {
        if (beanContainer.containsKey(clazz.getName())) {
            return (T) beanContainer.get(clazz.getName());
        }
        return null;
    }

    public static <T> T getBean(Class<T> clazz) {
        return springContext.getBean(clazz);
    }


    public static ISequence getSequenceBean() {
        return sequence;
    }

    public static ICache getCacheBean() {
        return cache;
    }

    public static IBranchLog getBranchLog() {
        return branchLog;
    }

    public static void setBranchLog(IBranchLog myBranchLog) {
        branchLog = myBranchLog;
    }

    public static Object getContainBean(Class<?> c) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor ctr = c.getConstructors()[0];
        Class[] parameterTypes = ctr.getParameterTypes();
        Object[] objs = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            objs[i] = springContext.getBean(parameterTypes[i]);
        }
        Object bean;
        if (objs.length > 0) {
            bean = ctr.newInstance(objs);
        } else {
            bean = ctr.newInstance();
        }
        return bean;
    }

    public static void init(IContext sc, String... packageNames) throws Exception {
        if (errorMsg != null) {
            throw new Exception(errorMsg.toString());
        }
        springContext = sc;
        Map<String, IRepository> mapRepository = new HashMap<>();
        for (String packageName : packageNames) {
            Reflections f = new Reflections(packageName.trim());
            Set<Class<?>> set = f.getTypesAnnotatedWith(DoRepository.class);
            for (Class<?> c : set) {
                if (c.getClass().equals(IRepository.class.getClass())) {
                    Object bean = Container.getContainBean(c);
                    Container.put(bean);
                    IRepository repository = (IRepository) bean;
                    ErrorCheck.checkException(!mapRepository.containsKey(repository.getTableName()), "仓库有重复的表名[" + repository.getTableName() + "]");
                    mapRepository.put(repository.getTableName(), repository);
                }
            }
        }

        ISequence sequence = sc.getBean(ISequence.class);
        if (sequence != null) {
            Container.sequence = sequence;
        }

        ICache cache = sc.getBean(ICache.class);
        if (cache != null) {
            Container.cache = cache;
        }

        IBranchLog branchLog = sc.getBean(IBranchLog.class);
        if (branchLog != null) {
            Container.branchLog = branchLog;
        }


        ErrorCheck.checkNotNullException(Container.sequence, "需要实现序例");
        ErrorCheck.checkNotNullException(Container.cache, "需要实现缓存");
        for (Map.Entry<String, IRepository> entry : mapRepository.entrySet()) {
            Container.getSequenceBean().init(entry.getValue());
        }
    }
}