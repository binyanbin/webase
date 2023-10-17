package com.bin.api.operate.base;

import com.bin.api.controller.param.BranchParam;
import com.bin.api.operate.domain.cache.ActiveBranchDo;
import com.bin.api.operate.domain.db.BranchDo;
import com.bin.api.utils.StrUtils;
import com.bin.api.web.base.OperateDef;
import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import com.bin.webase.core.operate.OperateId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CreatedBranchOp extends Operator<BranchParam> {
    @Override
    protected Result dispose(BranchParam param) {
        BranchDo branchDo = BranchDo.newInstance(param, getTime());
        save(branchDo);
        String code = StrUtils.generateNumberString(8);
        ActiveBranchDo activeBranchDo = new ActiveBranchDo(branchDo.getId(), code);
        save(activeBranchDo);
        return Result.success(activeBranchDo.getModel());
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.CREATED_BRANCH;
    }
}
