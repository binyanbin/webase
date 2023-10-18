package com.bin.api.controller.param;

import com.bin.webase.core.model.IParam;
import com.bin.webase.exception.ErrorCheck;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class TeacherParam implements IParam {
    private String name;
    private String introduce;
    private String detailIntroduce;
    private String school;


    @Override
    public void validate() {
        ErrorCheck.checkArgument(StringUtils.hasText(name), "老师名称不能为空");
        ErrorCheck.checkArgument(StringUtils.hasText(introduce), "老师简介不能为空");
        ErrorCheck.checkArgument(StringUtils.hasText(school), "老师毕业学校不能为空");
    }
}
