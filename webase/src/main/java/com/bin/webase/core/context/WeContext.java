package com.bin.webase.core.context;


import com.bin.webase.exception.ErrorCheck;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WeContext {

    private final static Map<String, IRepository> repositories = new HashMap<>();
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


    public static <T extends IRepository> T getRepository(Class<T> clazz) {
        if (repositories.containsKey(clazz.getName())) {
            return (T) repositories.get(clazz.getName());
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
        for (String packageName : packageNames) {
            Reflections f = new Reflections(packageName.trim());
            Set<Class<?>> set = f.getTypesAnnotatedWith(DoRepository.class);
            for (Class<?> c : set) {
                if (c.getClass().equals(IRepository.class.getClass())) {
                    Object bean = WeContext.getContainBean(c);
                    IRepository repository = (IRepository) bean;
                    ErrorCheck.checkException(!repositories.containsKey(repository.getTableName()), "仓库有重复的表名[" + repository.getTableName() + "]");
                    repositories.put(repository.getClass().getName(), repository);
                }
            }
        }

        ISequence sequence = sc.getBean(ISequence.class);
        if (sequence != null) {
            WeContext.sequence = sequence;
        }

        ICache cache = sc.getBean(ICache.class);
        if (cache != null) {
            WeContext.cache = cache;
        }

        IBranchLog branchLog = sc.getBean(IBranchLog.class);
        if (branchLog != null) {
            WeContext.branchLog = branchLog;
        }


        ErrorCheck.checkNotNullException(WeContext.sequence, "需要实现序例");
        ErrorCheck.checkNotNullException(WeContext.cache, "需要实现缓存");
        for (Map.Entry<String, IRepository> entry : repositories.entrySet()) {
            WeContext.getSequenceBean().init(entry.getValue());
        }
    }
}
