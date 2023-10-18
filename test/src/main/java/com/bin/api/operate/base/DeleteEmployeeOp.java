package com.bin.api.operate.base;

import com.bin.api.controller.param.IdParam;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.operate.domain.db.EmployeeDo;
import com.bin.api.web.base.OperateDef;
import com.bin.webase.core.model.OperateId;
import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import com.bin.webase.exception.ErrorCheck;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class DeleteEmployeeOp extends Operator<IdParam> {
    @Override
    protected Result dispose(IdParam param) {
        WebSession webSession = getToken(WebSession.class);
        EmployeeDo employee = new EmployeeDo(param.getId());
        ErrorCheck.checkArgument(!employee.isNull(), "员工未找到");
        if (employee.getUserId() == null || employee.getUserId() == 0L) {
            remove(employee);
        } else {
            employee.delete(getTime(), webSession.getEmployeeId());
            save(employee);
        }
        saveBranchLog(employee, param, "");
        return Result.success();
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.DELETE_EMPLOYEE;
    }
}
