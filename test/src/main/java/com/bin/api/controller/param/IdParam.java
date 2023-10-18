package com.bin.api.controller.param;


import com.bin.webase.core.model.IParam;

public class IdParam implements IParam {
    private Long id;

    public IdParam(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
