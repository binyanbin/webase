package com.bin.webase.core.entity;


import com.alibaba.fastjson.JSON;
import com.bin.webase.core.context.CacheDbRepository;
import com.bin.webase.core.context.IRepository;
import com.bin.webase.core.context.WebaseContext;
import com.bin.webase.core.operate.UnitWorkUtils;

import java.lang.reflect.*;

/**
 * 基于数据库存储的业务对象
 */
public abstract class DbDomain<T> implements UniqueId {
    protected T model;

    public DbDomain(Long id) {
        Type superClass = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
        Class<?> clazz = getRawType(type);
        String uniqueId = getUniqueId(clazz, id);
        DbDomain domain = UnitWorkUtils.getDbObject(uniqueId);
        if (domain != null && !domain.isNull()) {
            model = (T) domain.getModel();
        }
        if (model == null) {
            IRepository<T> repository = getRepository();
            if (repository instanceof CacheDbRepository) {
                String json = WebaseContext.getCacheBean().get(CacheDbRepository.PREFIX + uniqueId);
                if (json != null && json.length() > 0) {
                    model = JSON.parseObject(json, type);
                } else {
                    model = repository.getModel(id);
                }
            } else {
                model = repository.getModel(id);
            }
        }
    }

    public DbDomain(T model) {
        this.model = model;
    }

    public static String getUniqueId(Class<?> clazz, Long id) {
        return clazz.getTypeName() + id.toString();
    }

    // type不能直接实例化对象，通过type获取class的类型，然后实例化对象
    private static Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            return (Class) rawType;
        } else if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            return Array.newInstance(getRawType(componentType), 0).getClass();
        } else if (type instanceof TypeVariable) {
            return Object.class;
        } else if (type instanceof WildcardType) {
            return getRawType(((WildcardType) type).getUpperBounds()[0]);
        } else {
            String className = type == null ? "null" : type.getClass().getName();
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + className);
        }
    }

    public abstract IRepository<T> getRepository();

    public T getModel() {
        return model;
    }

    public boolean isNull() {
        return model == null;
    }

    public abstract Long getId();

    public abstract void setId(Long id);

    public String getTableName() {
        return getRepository().getTableName();
    }

    boolean equal(DbDomain obj) {
        return obj.getUniqueId().equals(getUniqueId());
    }

    @Override
    public String getUniqueId() {
        return getUniqueId(model.getClass(), getId());
    }

}
