package com.bin.api.controller.param;


import com.bin.webase.core.model.IParam;
import com.bin.webase.exception.ErrorCheck;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class ActiveEmployeeParam implements IParam {
    private Long id;
    private String activeCode;
    private String openId;
    private String phone;

    @Override
    public void validate() {
        ErrorCheck.checkArgument(id != null && id > 0L, "门店不能为空");
        ErrorCheck.checkArgument(StringUtils.hasText(activeCode), "激活码不能为空");
        ErrorCheck.checkArgument(StringUtils.hasText(phone), "激活码不能为空");
    }

}
