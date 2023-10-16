package com.bin.webase.domain.entity.statemachine;

import com.bin.webase.domain.operate.model.OperateId;

public class BizAction {
    private OperateId command;
    private BizStateId stateId;

    public BizAction(OperateId command, BizStateId state) {
        this.command = command;
        this.stateId = state;
    }

    public OperateId getCommand() {
        return command;
    }

    public BizStateId getState() {
        return stateId;
    }
}
