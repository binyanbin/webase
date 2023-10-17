package com.bin.api.operate.base;

import com.bin.api.controller.param.ActiveBranchParam;
import com.bin.api.operate.domain.cache.ActiveBranchDo;
import com.bin.api.operate.domain.db.BranchDo;
import com.bin.api.operate.domain.db.EmployeeDo;
import com.bin.api.operate.domain.db.UserDo;
import com.bin.api.web.base.OperateDef;

import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import com.bin.webase.core.operate.OperateId;
import com.bin.webase.exception.ErrorCheck;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ActiveBranchOp extends Operator<ActiveBranchParam> {
    @Override
    protected Result dispose(ActiveBranchParam param) {
        ActiveBranchDo activeBranchDo = new ActiveBranchDo(param.getBranchId());
        BranchDo branchDo = new BranchDo(param.getBranchId());
        ErrorCheck.checkArgument(!branchDo.isNull(), "门店未找到");
        ErrorCheck.checkArgument(!activeBranchDo.isNull(), "未找到激活码");
        ErrorCheck.checkArgument(activeBranchDo.getCode().equals(param.getActiveCode()), "激活码不正确");

        UserDo userDo = new UserDo(param.getAdminOpenId());
        if (userDo.isNull()) {
            userDo = UserDo.newInstance(param.getPhone(), getTime(), param.getAdminOpenId(), branchDo.getId());
        } else {
            userDo.bind(param.getPhone(), getTime(), branchDo.getId());
        }
        save(userDo);

        branchDo.active(getTime());
        save(branchDo);

        EmployeeDo employeeDo = EmployeeDo.newInstance(branchDo.getId(), userDo.getId(), param.getName(), 0L, 0L, getTime());
        save(employeeDo);
        remove(activeBranchDo);
        return Result.success();
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.ACTIVE_BRANCH;
    }
}
