package com.bin.api.controller.param;


import com.bin.webase.core.model.IParam;
import com.bin.webase.exception.ErrorCheck;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class ForgetPasswordParam implements IParam {
    private String phone;
    private String password;
    private String code;

    @Override
    public void validate() {
        ErrorCheck.checkArgument(StringUtils.hasText(phone), "手机号不存在");
        ErrorCheck.checkArgument(phone.trim().length() == 11, "手机格式不正确");
        ErrorCheck.checkArgument(StringUtils.hasText(code),"验证码不能为空");
        ErrorCheck.checkArgument(StringUtils.hasText(password),"密码不能为空");
    }
}
