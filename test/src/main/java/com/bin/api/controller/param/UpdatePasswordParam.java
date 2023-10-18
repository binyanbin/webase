package com.bin.api.controller.param;


import com.bin.webase.core.model.IParam;
import com.bin.webase.exception.ErrorCheck;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class UpdatePasswordParam implements IParam {
    private String password;
    private String newPassword;

    @Override
    public void validate() {
        ErrorCheck.checkArgument(StringUtils.hasText(password),"密码不能为空");
        ErrorCheck.checkArgument(StringUtils.hasText(newPassword),"新密码不能为空");
    }
}
