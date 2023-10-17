package com.bin.webase.core.entity;


import com.alibaba.fastjson.JSON;
import com.bin.webase.core.operate.UnitWorkUtils;
import com.bin.webase.core.context.WeContext;

/**
 * 基于缓存存储的业务对象
 */
public abstract class CacheDomain<T extends UniqueId> implements UniqueId {
    protected T model;

    public CacheDomain() {

    }

    public CacheDomain(T model) {
        this.model = model;
    }

    public CacheDomain(String id, Class<T> clazz) {
        if (id != null && id.length() > 0) {
            CacheDomain cacheDomain = UnitWorkUtils.getDomain(id);
            if (cacheDomain != null && !cacheDomain.isNull()) {
                this.model = (T) cacheDomain.getModel();
            } else {
                String json = WeContext.getCacheBean().get(id);
                this.model = JSON.parseObject(json, clazz);
            }
        }
    }

    public boolean isNull() {
        return model == null;
    }

    public String getUniqueId() {
        return model.getUniqueId();
    }

    public abstract long getSaveTime();

    public T getModel() {
        return model;
    }

    public String toJson() {
        return JSON.toJSONString(model);
    }

}
