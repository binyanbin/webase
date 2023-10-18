package com.bin.api.operate.base;


import com.bin.api.controller.param.ActiveEmployeeParam;
import com.bin.api.dao.mybatis.model.Employee;
import com.bin.api.operate.domain.cache.ActiveEmployeeDo;
import com.bin.api.operate.domain.db.EmployeeDo;
import com.bin.api.operate.domain.db.UserDo;
import com.bin.api.web.base.OperateDef;
import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import com.bin.webase.core.model.OperateId;
import com.bin.webase.exception.ErrorCheck;
import com.bin.webase.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ActiveEmployeeOp extends Operator<ActiveEmployeeParam> {

    @Override
    protected Result dispose(ActiveEmployeeParam param) {
        EmployeeDo employee = new EmployeeDo(param.getId());
        ErrorCheck.check(!employee.isNull(), ErrorCode.ArgumentsIncorrect, "未找到人员");

        ActiveEmployeeDo activeEmployee = new ActiveEmployeeDo(param.getId());
        ErrorCheck.checkArgument(!activeEmployee.isNull(), "未找到激活码");
        ErrorCheck.checkArgument(activeEmployee.getCode().equals(param.getActiveCode()), "激活码不正确");

        UserDo user = new UserDo(param.getOpenId());

        List<Employee> employeeList = EmployeeDo.REPOSITORY.listByUserId(user.getId());
        ErrorCheck.checkArgument(CollectionUtils.isEmpty(employeeList), "用户已绑定");


        if (user.isNull()) {
            user = UserDo.newInstance(param.getPhone(), getTime(), param.getOpenId(), employee.getBranchId());
        } else {
            user.bind(param.getPhone(), getTime(), employee.getBranchId());
        }
        save(user);

        employee.active(user.getId(), getTime());
        save(employee);

        remove(activeEmployee);
        return Result.success();
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.ACTIVE_EMPLOYEE;
    }
}
