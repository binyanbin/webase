package com.bin.webase.domain.operate;


import com.bin.webase.domain.entity.statemachine.BizStateMachine;
import com.bin.webase.domain.entity.statemachine.IState;
import com.bin.webase.domain.operate.model.OperateId;

import java.util.Date;

public abstract class Method extends BaseContainer {

    protected OperateId operateId;

    public Method(OperateId operateId) {
        this.operateId = operateId;
    }

    public abstract void commit(Date now);

    protected void updateState(IState domain) {
        BizStateMachine.disposeState(domain, operateId);
    }

}
