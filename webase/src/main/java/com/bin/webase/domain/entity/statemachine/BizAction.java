package com.bin.webase.domain.entity.statemachine;

import com.bin.webase.domain.command.model.command.CommandId;

public class BizAction {
    private CommandId command;
    private BizStateId stateId;

    public BizAction(CommandId command, BizStateId state) {
        this.command = command;
        this.stateId = state;
    }

    public CommandId getCommand() {
        return command;
    }

    public BizStateId getState() {
        return stateId;
    }
}
