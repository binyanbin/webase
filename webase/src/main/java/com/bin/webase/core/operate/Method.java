package com.bin.webase.core.operate;


import com.bin.webase.core.context.WeContext;
import com.bin.webase.core.context.IBranchLog;
import com.bin.webase.core.entity.DbDomain;
import com.bin.webase.core.entity.statemachine.BizStateMachine;
import com.bin.webase.core.entity.statemachine.IState;

import java.util.Date;

public abstract class Method extends BaseOperate {

    protected OperateId operateId;

    public Method(OperateId operateId) {
        this.operateId = operateId;
    }

    public abstract void commit(Date now);

    protected void updateState(IState domain) {
        BizStateMachine.disposeState(domain, operateId);
    }

    protected void saveBranchLog(String msg, IParam param, DbDomain domain) {
        IBranchLog branchLog = WeContext.getBranchLog();
        if (branchLog != null) {
            WeContext.getBranchLog().newBranchLog(operateId, domain, param, msg);
        }
    }

    protected void saveBranchLog(DbDomain domain, IParam param) {
        IBranchLog branchLog = WeContext.getBranchLog();
        if (branchLog != null) {
            WeContext.getBranchLog().newBranchLog(operateId, domain, param, "");
        }
    }

    protected void saveBranchLog(DbDomain domain) {
        IBranchLog branchLog = WeContext.getBranchLog();
        if (branchLog != null) {
            WeContext.getBranchLog().newBranchLog(operateId, domain, null, "");
        }
    }

}
