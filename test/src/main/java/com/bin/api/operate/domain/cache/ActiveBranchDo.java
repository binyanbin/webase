package com.bin.api.operate.domain.cache;

import com.alibaba.fastjson.JSON;
import com.bin.webase.core.context.WebaseContext;
import com.bin.webase.core.entity.CacheDomain;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

public class ActiveBranchDo extends CacheDomain<ActiveBranch> {

    public ActiveBranchDo(Long branchId, String code) {
        model = new ActiveBranch(branchId, code);
    }

    public ActiveBranchDo(Long branchId) {
        String json = WebaseContext.getCacheBean().get(ActiveBranch.PREFIX + branchId);
        if (StringUtils.hasText(json)) {
            model = JSON.parseObject(json, ActiveBranch.class);
        }
    }

    public String getCode() {
        return model.getCode();
    }

    public long getSaveTime() {
        return TimeUnit.DAYS.toSeconds(1);
    }

}
