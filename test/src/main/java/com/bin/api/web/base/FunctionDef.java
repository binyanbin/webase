package com.bin.api.web.base;

import com.bin.webase.core.model.FunctionId;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FunctionDef {

    public static final FunctionId GUEST_VISIT = FunctionId.def(11, "游客访问");
    public static final FunctionId MEMBER_FUNCTION_ID = FunctionId.def(12, "会员权限");

    public static final FunctionId EMPLOYEE_MANAGE = FunctionId.def(20, "人员管理");
    public static final FunctionId COURSE_MANAGE = FunctionId.def(22, "课程管理");
    public static final FunctionId CAMPUS_MANAGE = FunctionId.def(23, "校区管理");
    public static final FunctionId CLASS_TYPE_MANAGE = FunctionId.def(24, "课程类型");
}