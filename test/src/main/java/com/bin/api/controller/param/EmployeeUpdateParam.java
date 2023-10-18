package com.bin.api.controller.param;


import com.bin.webase.core.model.IParam;
import com.bin.webase.exception.ErrorCheck;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class EmployeeUpdateParam implements IParam {
    private String name;
    private String sex;


    public void validate() {
        ErrorCheck.checkArgument(StringUtils.hasText(name), "名称不能为空");
        ErrorCheck.checkArgument(sex.equals("1") || sex.equals("2"), "性别不正确");
    }
}
