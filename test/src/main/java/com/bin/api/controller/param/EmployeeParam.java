package com.bin.api.controller.param;


import com.bin.webase.core.operate.IParam;
import com.bin.webase.exception.ErrorCheck;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class EmployeeParam implements IParam {
    private String name;
    private boolean admin;

    public void validate() {
        ErrorCheck.checkArgument(StringUtils.hasText(name), "名称不能为空");
    }
}
