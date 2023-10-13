package com.bin.webase.domain.entity.statemachine;

import com.bin.webase.domain.command.model.command.BaseCommand;
import com.bin.webase.domain.command.model.command.CommandId;
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

    public BizState addAction(CommandId command, BizStateId resultState) {
        if (command != CommandId.NOOP) {
            validate(command);
            actions.add(new BizAction(command, resultState));
        }
        return this;
    }

    public BizState addAction(CommandId commandId) {
        validate(commandId);
        actions.add(new BizAction(commandId, null));
        return this;
    }

    private void validate(CommandId commandId) {
        for (BizAction bizAction : this.actions) {
            if (bizAction.getCommand().equals(commandId)) {
                throw new ApplicationException(ErrorCode.StateMachineSameGroupId);
            }
        }
    }

    BizAction getAction(BaseCommand baseCommand) {
        for (BizAction bizAction : this.actions) {
            if (bizAction.getCommand().equals(baseCommand.getCommandId())) {
                return bizAction;
            }
        }
        return null;
    }
}
