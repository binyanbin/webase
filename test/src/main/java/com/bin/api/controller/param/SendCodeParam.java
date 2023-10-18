package com.bin.api.controller.param;


import com.bin.api.dao.enums.SmsType;
import com.bin.webase.core.model.IParam;
import com.bin.webase.exception.ErrorCheck;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class SendCodeParam implements IParam {
    private String phone;
    private String sign;
    private SmsType type;
    private Long branchId;

    @Override
    public void validate() {
        ErrorCheck.checkArgument(StringUtils.hasText(phone), "手机号不存在");
        ErrorCheck.checkArgument(phone.trim().length() == 11, "手机格式不正确");
        ErrorCheck.checkArgument(StringUtils.hasText(sign), "答名不能为空");
        ErrorCheck.checkArgument(type != null, "验证码类型不能为空");
        ErrorCheck.checkArgument(branchId!=null,"弌银行卡");
    }
}
