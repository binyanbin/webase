package com.bin.webase.core.context;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class CacheDbRepository<T> implements IRepository<T>, IBranchRepository<T> {

    public static final String PREFIX = "db_";
    public static final String BRANCH_PREFIX = "db_branch_";

    public List<T> listCacheByBranchId(Long branchId, Class<T> clazz) {
        String key = BRANCH_PREFIX + clazz.getTypeName() + branchId;
        if (WeContext.getCacheBean().hasKey(key)) {
            String json = WeContext.getCacheBean().get(key);
            return JSON.parseArray(json, clazz);
        }
        List<T> result = listByBranchId(branchId);
        WeContext.getCacheBean().set(key, JSON.toJSONString(result), TimeUnit.DAYS.toSeconds(1));
        return result;
    }

    public T getCmodel(Long id, Class<T> clazz) {
        String key = PREFIX + clazz.getTypeName() + id;
        if (WeContext.getCacheBean().hasKey(key)) {
            String json = WeContext.getCacheBean().get(key);
            return JSON.parseObject(json, clazz);
        } else {
            T result = getModel(id);
            if (result != null) {
                WeContext.getCacheBean().set(key, JSON.toJSONString(result));
                return result;
            }
            return null;
        }
    }

}
