package com.bin.api.controller.param;


import com.bin.webase.core.model.IParam;
import com.bin.webase.exception.ErrorCheck;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class UserLoginParam implements IParam {
    private String openId;


    @Override
    public void validate() {
        ErrorCheck.checkArgument(StringUtils.hasText(openId), "openId不存在");
    }

}
