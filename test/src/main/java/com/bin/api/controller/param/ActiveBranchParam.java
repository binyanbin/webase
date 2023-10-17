package com.bin.api.controller.param;

import com.bin.webase.core.operate.IParam;
import com.bin.webase.exception.ErrorCheck;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class ActiveBranchParam implements IParam {
    private Long branchId;
    private String activeCode;
    private String adminOpenId;
    private String phone;
    private String name;

    @Override
    public void validate() {
        ErrorCheck.checkArgument(StringUtils.hasText(adminOpenId), "openId不能为空");
        ErrorCheck.checkArgument(StringUtils.hasText(activeCode), "激活码不能为空");
        ErrorCheck.checkArgument(branchId != null && branchId > 0L, "门店不能为空");
        ErrorCheck.checkArgument(StringUtils.hasText(phone), "手机不能为空");
        ErrorCheck.checkArgument(phone.trim().length() == 11, "手机号不正确");
        ErrorCheck.checkArgument(StringUtils.hasText(name), "名称不能为空");
    }

}
