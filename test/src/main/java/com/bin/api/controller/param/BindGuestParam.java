package com.bin.api.controller.param;

import com.bin.webase.core.model.IParam;
import lombok.Data;

@Data
public class BindGuestParam implements IParam {
    private String phone;
    private String name;

    @Override
    public void validate() {

    }
}
