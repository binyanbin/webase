package com.bin.api.controller.param;

import com.bin.webase.core.operate.IParam;
import com.bin.webase.exception.ErrorCheck;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class ClassTypeParam implements IParam {
    private Long id;
    private String name;
    private String description;

    @Override
    public void validate() {
        ErrorCheck.checkArgument(StringUtils.hasText(name), "名称不能为空");
        ErrorCheck.checkArgument(StringUtils.hasText(description), "描述不能为空");
    }
}
