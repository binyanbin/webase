package com.bin.api.controller.param;

import com.bin.webase.core.operate.IParam;
import com.bin.webase.exception.ErrorCheck;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class BindUserParam implements IParam {
    private String openId;
    private String phone;
    private String name;
    private Long branchId;

    @Override
    public void validate() {
        ErrorCheck.checkArgument(StringUtils.hasText(name), "名称不能为空");
    }
}
