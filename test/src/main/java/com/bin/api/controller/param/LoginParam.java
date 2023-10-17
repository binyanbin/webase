package com.bin.api.controller.param;

import com.bin.webase.core.operate.IParam;
import lombok.Data;

@Data
public class LoginParam implements IParam {
    private String phone;
    private String password;
    private Long branchId;

    @Override
    public void validate() {

    }
}
