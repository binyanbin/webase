package com.bin.api.operate.base;

import com.bin.api.controller.param.EmployeeParam;
import com.bin.api.operate.domain.cache.ActiveEmployeeDo;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.operate.domain.db.EmployeeDo;
import com.bin.api.utils.StrUtils;
import com.bin.api.web.base.OperateDef;

import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import com.bin.webase.core.model.OperateId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AddEmployeeOp extends Operator<EmployeeParam> {
    @Override
    protected Result dispose(EmployeeParam param) {
        WebSession webSession = getToken(WebSession.class);
        EmployeeDo employee = EmployeeDo.newInstance(webSession.getBranchId(), 0L, param.getName(), webSession.getEmployeeId(), 0L, getTime());
        save(employee);
        String code = StrUtils.generateNumberString(8);
        ActiveEmployeeDo activeEmployee = new ActiveEmployeeDo(employee.getId(), code);
        save(activeEmployee);
        saveBranchLog(employee, param,"");
        return new Result(activeEmployee.getModel());
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.ADD_EMPLOYEE;
    }
}
