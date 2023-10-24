package com.bin.webase.core.context;


import com.bin.webase.core.web.DisposeApiRequest;
import com.bin.webase.core.web.WebContext;
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
    private static ICacheRepository cache;
    private static IOperateLog operateLog;
    private static IQueryLog queryLog;
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

    public static ICacheRepository getCacheBean() {
        return cache;
    }

    public static IOperateLog getOperateLog() {
        return operateLog;
    }

    public static IQueryLog getQueryLog() {
        return queryLog;
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

        ICacheRepository cache = sc.getBean(ICacheRepository.class);
        if (cache != null) {
            WeContext.cache = cache;
        }

        IOperateLog operateLog = sc.getBean(IOperateLog.class);
        if (operateLog != null) {
            WeContext.operateLog = operateLog;
        }

        IQueryLog queryLog = sc.getBean(IQueryLog.class);
        if (queryLog != null) {
            WeContext.queryLog = queryLog;
        }

        DisposeApiRequest disposeApiRequest = sc.getBean(DisposeApiRequest.class);
        if (disposeApiRequest == null) {
            throw new Exception("未实例DisposeApiRequest");
        }


        ErrorCheck.checkNotNullException(WeContext.sequence, "需要实现序例");
        ErrorCheck.checkNotNullException(WeContext.cache, "需要实现缓存");
        for (Map.Entry<String, IRepository> entry : repositories.entrySet()) {
            WeContext.getSequenceBean().init(entry.getValue());
        }
    }
}
