package com.bin.webase.core.entity.statemachine;

import com.bin.webase.core.model.OperateId;
import com.bin.webase.exception.ApplicationException;
import com.bin.webase.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

public class BizState {
    private BizStateId id;
    private List<BizAction> actions;

    public BizState(BizStateId stateId) {
        this.id = stateId;
        this.actions = new ArrayList<>();
    }

    public List<BizAction> getActions() {
        return actions;
    }

    public BizStateId getId() {
        return id;
    }

    public BizState addAction(OperateId command, BizStateId resultState) {
        if (command != OperateId.NOOP) {
            validate(command);
            actions.add(new BizAction(command, resultState));
        }
        return this;
    }

    public BizState addAction(OperateId operateId) {
        validate(operateId);
        actions.add(new BizAction(operateId, null));
        return this;
    }

    private void validate(OperateId operateId) {
        for (BizAction bizAction : this.actions) {
            if (bizAction.getCommand().equals(operateId)) {
                throw new ApplicationException(ErrorCode.StateMachineSameGroupId);
            }
        }
    }

    BizAction getAction(OperateId operateId) {
        for (BizAction bizAction : this.actions) {
            if (bizAction.getCommand().equals(operateId)) {
                return bizAction;
            }
        }
        return null;
    }
}
