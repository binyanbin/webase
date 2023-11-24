package com.bin.api.operate.domain.cache;

import com.alibaba.fastjson.JSON;
import com.bin.webase.core.context.WebaseContext;
import com.bin.webase.core.entity.CacheDomain;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

public class ActiveEmployeeDo extends CacheDomain<ActiveEmployee> {

    public ActiveEmployeeDo(Long employeeId, String code) {
        model = new ActiveEmployee(employeeId, code);
    }

    public ActiveEmployeeDo(Long branchId) {
        String json = WebaseContext.getCacheBean().get(ActiveEmployee.PREFIX + branchId);
        if (StringUtils.hasText(json)) {
            model = JSON.parseObject(json, ActiveEmployee.class);
        }
    }

    public String getCode() {
        return model.getCode();
    }

    public long getSaveTime() {
        return TimeUnit.DAYS.toSeconds(1);
    }
}
