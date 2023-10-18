package com.bin.api.web.base;

import com.bin.webase.core.model.OperateId;
import org.springframework.context.annotation.Configuration;

@Configuration
public  class OperateDef {
    public static final OperateId SCAN_BRANCH = OperateId.def(9999, "门店扫码");
    public static final OperateId CREATED_BRANCH = OperateId.def(10000, "创建门店");
    public static final OperateId LOGIN = OperateId.def(10001, "小程序登陆");
    public static final OperateId LOGIN_PASSWORD = OperateId.def(9998, "密码登陆");
    public static final OperateId LOGOUT = OperateId.def(10002, "登出");
    public static final OperateId SWITCH_BRANCH = OperateId.def(10003, "管理切换门店");
    public static final OperateId ACTIVE_EMPLOYEE = OperateId.def(10005, "激活员工");
    public static final OperateId ACTIVE_BRANCH = OperateId.def(10006, "门店激活");
    public static final OperateId BIND_GUEST = OperateId.def(10007, "客户绑定");
    public static final OperateId ADD_EMPLOYEE = OperateId.def(10008, "新增员工");
    public static final OperateId GUEST_SWITCH_BRANCH = OperateId.def(10009, "客户切换门店");
    public static final OperateId DELETE_EMPLOYEE = OperateId.def(10010, "删除员工");
    public static final OperateId ADD_COURSE = OperateId.def(10016, "新增课程");
    public static final OperateId UPDATE_COURSE = OperateId.def(10017, "修改课程");
    public static final OperateId DELETE_COURSE = OperateId.def(10018, "删除课程");
    public static final OperateId ENABLE_COURSE = OperateId.def(10019, "启用课程");
    public static final OperateId DISABLE_COURSE = OperateId.def(10020, "禁用课程");

    public static final OperateId ADD_CAMPUS = OperateId.def(10021, "新增校区");
    public static final OperateId UPDATE_CAMPUS = OperateId.def(10022, "修改校区");
    public static final OperateId DELETE_CAMPUS = OperateId.def(10023, "删除校区");
    public static final OperateId ENABLE_CAMPUS = OperateId.def(10024, "启用校区");
    public static final OperateId DISABLE_CAMPUS = OperateId.def(10025, "禁用校区");
    public static final OperateId ADD_CLASS_TYPE = OperateId.def(10026, "新增课程类型");
    public static final OperateId UPDATE_CLASS_TYPE = OperateId.def(10027, "修改课程类型");
    public static final OperateId DELETE_CLASS_TYPE = OperateId.def(10028, "删除课程类型");
    public static final OperateId ENABLE_CLASS_TYPE = OperateId.def(10029, "启用课程类型");
    public static final OperateId DISABLE_CLASS_TYPE = OperateId.def(10030, "禁用课程类型");
    public static final OperateId BRANCH_SETTING = OperateId.def(10031, "设置门店信息");
    public static final OperateId BRANCH_POSITION_SETTING = OperateId.def(10032, "设置门店位置");
}