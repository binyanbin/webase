package com.bin.webase.core.context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bin.webase.core.unitwork.UnitWork;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BranchCacheRepository<T> implements IRepository<T> {

    public abstract List<T> listByBranchId(Long branchId);

    public List<T> listCacheByBranchId(Long branchId) {
        String className = new TypeReference<T>() {
        }.getType().getTypeName();
        String key = UnitWork.CACHE_PREFIX + className + branchId;
        if (WeContext.getCacheBean().hasKey(key)) {
            String json = WeContext.getCacheBean().get(key);
            return JSON.parseObject(json, new TypeReference<List<T>>() {
            }.getType());
        } else {
            List<T> result = listByBranchId(branchId);
            if (result != null && result.size() > 0) {
                WeContext.getCacheBean().set(key, JSON.toJSONString(result), TimeUnit.DAYS.toSeconds(1));
            }
            return result;
        }
    }
}
