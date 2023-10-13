package com.bin.webase.domain.dto;

import com.alibaba.fastjson.JSON;
import com.bin.webase.domain.container.DomainRegistry;
import com.bin.webase.domain.content.FunctionObject;
import com.bin.webase.exception.ApplicationException;
import com.bin.webase.exception.ErrorCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class LimitedDTO<T> extends FunctionObject {

    private T data;
    private Integer code;
    private Date begin;
    private String key;

    private static final String LIMIT_KEY = "_limit";
    private static final String DATA_KEY = "_data";
    private static final Integer LIMIT_TIME_SPAN = 3;

    private String getLimitKey() {
        return this.getClass().getTypeName() + "_" + key + LIMIT_KEY;
    }

    private String getDateKey() {
        return this.getClass().getTypeName() + "_" + key + DATA_KEY;
    }

    public LimitedDTO(String key) {
        super();
        this.key = key;
        if (DomainRegistry.getCacheBean().hasKey(getLimitKey())) {
            throw new ApplicationException(ErrorCode.ServerBusy);
        }
        this.code = 0;
        begin = new Date();
    }

    public T getData() {
        return data;
    }

    public void setResult(T data) {
        this.data = data;
        String val = DomainRegistry.getCacheBean().get(getDateKey());
        Date end = new Date();
        Long timeSpan = end.getTime() - begin.getTime();
        List<Long> saveTimeSpan  = new ArrayList<>(1);
        if (val != null && val.length() > 0) {
            saveTimeSpan = JSON.parseArray(val, Long.class);
            saveTimeSpan.add(timeSpan);
        } else {
            saveTimeSpan.add(timeSpan);
        }

        Long total = 0L;
        for (Long i : saveTimeSpan) {
            total = total + i;
        }

        Long average = total / saveTimeSpan.size();

        if (average > getMaxTimeSpan()
                && saveTimeSpan.size() > getMinCount()) {
            DomainRegistry.getCacheBean().set(getLimitKey(), average.toString(), LIMIT_TIME_SPAN);
        }
        DomainRegistry.getCacheBean().set(getLimitKey(), JSON.toJSONString(saveTimeSpan), LIMIT_TIME_SPAN);
    }


    public Integer getCode() {
        return code;
    }

    protected abstract Long getMaxTimeSpan();

    protected abstract Integer getMinCount();
}
