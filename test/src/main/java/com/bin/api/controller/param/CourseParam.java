package com.bin.api.controller.param;

import com.bin.webase.core.model.IParam;
import com.bin.webase.exception.ErrorCheck;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class CourseParam implements IParam {
    private Long id;
    private String name;
    private String period;
    private String needToKnow;
    private String introduce;
    private Long classTypeId;

    @Override
    public void validate() {
        ErrorCheck.checkArgument(StringUtils.hasText(name), "名称不能为空");
        ErrorCheck.checkArgument(StringUtils.hasText(period), "周期不能为空");
        ErrorCheck.checkArgument(StringUtils.hasText(needToKnow), "需知不能为空");
        ErrorCheck.checkArgument(StringUtils.hasText(introduce), "介绍不能为空");
        ErrorCheck.checkArgument(classTypeId != null && classTypeId > 0L, "课程类型必选");
    }
}
