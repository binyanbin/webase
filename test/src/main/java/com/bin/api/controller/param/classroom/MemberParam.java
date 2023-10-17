package com.bin.api.controller.param.classroom;

import com.bin.webase.core.operate.IParam;
import lombok.Data;

@Data
public class MemberParam implements IParam {
    private String phone;
    private String name;

    @Override
    public void validate() {

    }
}
