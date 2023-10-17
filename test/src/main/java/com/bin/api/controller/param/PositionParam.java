package com.bin.api.controller.param;

import com.bin.webase.core.operate.IParam;
import com.bin.webase.exception.ErrorCheck;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class PositionParam implements IParam {
    private String longitude;
    private String latitude;

    @Override
    public void validate() {
        ErrorCheck.checkArgument(StringUtils.hasText(longitude), "经度不能为空");
        ErrorCheck.checkArgument(StringUtils.hasText(latitude), "纬度不能为空");
    }
}
