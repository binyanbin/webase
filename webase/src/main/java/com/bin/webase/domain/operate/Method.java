package com.bin.webase.domain.operate;


import com.bin.webase.domain.container.Container;
import com.bin.webase.domain.container.IBranchLog;
import com.bin.webase.domain.entity.DbDomain;
import com.bin.webase.domain.entity.statemachine.BizStateMachine;
import com.bin.webase.domain.entity.statemachine.IState;
import com.bin.webase.domain.operate.model.IParam;
import com.bin.webase.domain.operate.model.OperateId;

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
        IBranchLog branchLog = Container.getBranchLog();
        if (branchLog != null) {
            Container.getBranchLog().newBranchLog(operateId, domain, param, msg);
        }
    }

    protected void saveBranchLog(DbDomain domain, IParam param) {
        IBranchLog branchLog = Container.getBranchLog();
        if (branchLog != null) {
            Container.getBranchLog().newBranchLog(operateId, domain, param, "");
        }
    }

    protected void saveBranchLog(DbDomain domain) {
        IBranchLog branchLog = Container.getBranchLog();
        if (branchLog != null) {
            Container.getBranchLog().newBranchLog(operateId, domain, null, "");
        }
    }

}
