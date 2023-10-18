package com.bin.api.controller.param;

import com.bin.webase.core.model.IParam;
import com.bin.webase.exception.ErrorCheck;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class BranchParam implements IParam {
    private String name;
    private String introduce;
    private String address;
    private String phone;


    @Override
    public void validate() {
        ErrorCheck.checkArgument(StringUtils.hasText(name), "名称不能为空");
        ErrorCheck.checkArgument(StringUtils.hasText(address), "地址不能为空");
        ErrorCheck.checkArgument(StringUtils.hasText(introduce), "介绍不能为空");
    }

}
