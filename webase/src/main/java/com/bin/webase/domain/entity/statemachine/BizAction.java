package com.bin.webase.domain.entity.statemachine;

import com.bin.webase.domain.command.model.command.CommandType;

public class BizAction {
    private CommandType command;
    private BizStateId stateId;

    public BizAction(CommandType command, BizStateId state) {
        this.command = command;
        this.stateId = state;
    }

    public CommandType getCommand() {
        return command;
    }

    public BizStateId getState() {
        return stateId;
    }
}
