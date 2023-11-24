package com.bin.webase.core.operate;


import com.bin.webase.core.context.WebaseContext;
import com.bin.webase.core.context.IOperateLog;
import com.bin.webase.core.entity.DbDomain;
import com.bin.webase.core.entity.statemachine.BizStateMachine;
import com.bin.webase.core.entity.statemachine.IState;
import com.bin.webase.core.model.IParam;
import com.bin.webase.core.model.OperateId;

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
        IOperateLog branchLog = WebaseContext.getOperateLog();
        if (branchLog != null) {
            WebaseContext.getOperateLog().log(operateId, domain, param, msg);
        }
    }

    protected void saveBranchLog(DbDomain domain, IParam param) {
        IOperateLog branchLog = WebaseContext.getOperateLog();
        if (branchLog != null) {
            WebaseContext.getOperateLog().log(operateId, domain, param, "");
        }
    }

    protected void saveBranchLog(DbDomain domain) {
        IOperateLog branchLog = WebaseContext.getOperateLog();
        if (branchLog != null) {
            WebaseContext.getOperateLog().log(operateId, domain, null, "");
        }
    }

}
