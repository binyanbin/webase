package com.bin.webase.domain.command.model;

import com.bin.webase.exception.ApplicationException;
import com.bin.webase.exception.ErrorCode;

import java.util.List;

public class IdListParam implements IParam {
    public IdListParam(List<Long> ids) {
        this.ids = ids;
    }

    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public void validate() {
        if (ids == null || ids.size() == 0) {
            new ApplicationException(ErrorCode.NoData);
        }
    }
}