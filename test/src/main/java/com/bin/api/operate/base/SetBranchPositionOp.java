package com.bin.api.operate.base;

import com.bin.api.controller.param.PositionParam;
import com.bin.api.operate.domain.db.BranchDo;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.web.base.OperateDef;
import com.bin.webase.core.operate.Operator;

import com.bin.webase.core.operate.Result;
import com.bin.webase.core.operate.OperateId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SetBranchPositionOp extends Operator<PositionParam> {
    @Override
    protected Result dispose(PositionParam param) {
        WebSession webSession = getToken(WebSession.class);

        BranchDo branchDo = new BranchDo(webSession.getBranchId());
        branchDo.setPosition(param);
        save(branchDo);
        saveBranchLog(branchDo);
        return Result.success();
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.BRANCH_POSITION_SETTING;
    }
}
