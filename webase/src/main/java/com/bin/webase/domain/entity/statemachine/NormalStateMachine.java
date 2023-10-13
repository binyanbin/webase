package com.bin.webase.domain.entity.statemachine;

import com.bin.webase.domain.command.model.command.CommandId;

import java.util.ArrayList;
import java.util.List;

public class NormalStateMachine implements IStateMachine {
    private static final BizStateId ENABLE = BizStateId.newS(1, "正常");
    private static final BizStateId DISABLE = BizStateId.newS(0, "禁用");
    private static final BizStateId DELETE = BizStateId.newS(2, "删除");

    private BizStateMachine bizStateMachine;
    private CommandId disableCommand;
    private CommandId deleteCommand;
    private CommandId updateCommand;
    private CommandId addCommand;

    public static List<Integer> noDelete() {
        List<Integer> result = new ArrayList<>(2);
        result.add(ENABLE.getId());
        result.add(DISABLE.getId());
        return result;
    }

    public static BizStateId getBizState(Integer stateId) {
        if (ENABLE.getId().equals(stateId)) {
            return ENABLE;
        } else if (DISABLE.getId().equals(stateId)) {
            return DISABLE;
        } else if (DELETE.getId().equals(stateId)) {
            return DELETE;
        }
        return null;
    }

    public NormalStateMachine(CommandId add, CommandId update, CommandId delete, CommandId disable) {
        this.addCommand = add;
        this.updateCommand = update;
        this.deleteCommand = delete;
        this.disableCommand = disable;
        bizStateMachine = new BizStateMachine().
                addFirstAction(new BizAction(addCommand, NormalStateMachine.ENABLE)).
                addState(new BizState(NormalStateMachine.ENABLE)
                        .addAction(deleteCommand, NormalStateMachine.DELETE)
                        .addAction(disableCommand, NormalStateMachine.DISABLE)
                        .addAction(updateCommand, NormalStateMachine.ENABLE))
                .addState(new BizState(NormalStateMachine.DISABLE)
                        .addAction(deleteCommand, NormalStateMachine.DELETE))
                .addState(new BizState(NormalStateMachine.DELETE)
                        .addAction(CommandId.NOOP));
    }

    public NormalStateMachine(CommandId add, CommandId update, CommandId delete) {
        this.addCommand = add;
        this.updateCommand = update;
        this.deleteCommand = delete;
        this.disableCommand = CommandId.NOOP;
        bizStateMachine = new BizStateMachine().
                addFirstAction(new BizAction(addCommand, NormalStateMachine.ENABLE)).
                addState(new BizState(NormalStateMachine.ENABLE)
                        .addAction(deleteCommand, NormalStateMachine.DELETE)
                        .addAction(updateCommand, NormalStateMachine.ENABLE))
                .addState(new BizState(NormalStateMachine.DELETE)
                        .addAction(CommandId.NOOP));
    }

    public NormalStateMachine(CommandId add, CommandId delete) {
        this.addCommand = add;
        this.deleteCommand = delete;
        this.disableCommand = this.updateCommand = CommandId.NOOP;
        bizStateMachine = new BizStateMachine().
                addFirstAction(new BizAction(addCommand, NormalStateMachine.ENABLE)).
                addState(new BizState(NormalStateMachine.ENABLE)
                        .addAction(deleteCommand, NormalStateMachine.DELETE))
                .addState(new BizState(NormalStateMachine.DELETE)
                        .addAction(CommandId.NOOP));
    }


    @Override
    public BizStateId parse(Integer stateId) {
        return bizStateMachine.parse(stateId);
    }

    @Override
    public BizState getState(Integer stateId) {
        return bizStateMachine.getState(stateId);
    }

    @Override
    public List<BizAction> getFirstAction() {
        return bizStateMachine.listAction();
    }


}
