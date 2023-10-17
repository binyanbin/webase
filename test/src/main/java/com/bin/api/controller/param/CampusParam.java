package com.bin.api.controller.param;

import com.bin.webase.core.operate.IParam;
import com.bin.webase.exception.ErrorCheck;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class CampusParam implements IParam {
    private Long id;
    private String name;
    private String address;

    @Override
    public void validate() {
        ErrorCheck.checkArgument(StringUtils.hasText(name), "名称不能为空");
        ErrorCheck.checkArgument(StringUtils.hasText(address), "地址不能为空");
    }

}
