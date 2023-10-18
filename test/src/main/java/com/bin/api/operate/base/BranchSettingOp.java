package com.bin.api.operate.base;

import com.bin.api.controller.param.BranchParam;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.operate.domain.db.BranchDo;
import com.bin.api.web.base.OperateDef;
import com.bin.webase.core.model.OperateId;
import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class BranchSettingOp extends Operator<BranchParam> {
    @Override
    protected Result dispose(BranchParam param) {
        WebSession webSession = getToken(WebSession.class);

        BranchDo branchDo = new BranchDo(webSession.getBranchId());
        branchDo.update(param, webSession.getEmployeeId(), getTime());
        save(branchDo);
        saveBranchLog(branchDo, param, "");
        return Result.success();
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.BRANCH_SETTING;
    }
}
