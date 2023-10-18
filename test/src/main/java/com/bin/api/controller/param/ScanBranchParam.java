package com.bin.api.controller.param;


import com.bin.webase.core.model.IParam;
import com.bin.webase.exception.ErrorCheck;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class ScanBranchParam implements IParam {
    private String openId;
    private Long branchId;

    @Override
    public void validate() {
        ErrorCheck.checkArgument(StringUtils.hasText(openId), "openId不能为空");
        ErrorCheck.checkArgument(branchId != null, "门店id不能为空");
    }
}
