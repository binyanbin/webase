package com.bin.webase.domain.entity.statemachine;

import com.bin.webase.domain.command.model.command.BaseCommand;
import com.bin.webase.exception.ApplicationException;
import com.bin.webase.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务状态机:验证操作，更新状态
 */
public class BizStateMachine {
    private List<BizState> bizStates;
    private List<BizAction> firstActions;

    public BizStateMachine() {
        this.bizStates = new ArrayList<>();
        this.firstActions = new ArrayList<>();
    }

    public BizStateMachine addFirstAction(BizAction firstAction) {
        this.firstActions.add(firstAction);
        return this;
    }

    public BizStateMachine addState(BizState bizState) {
        this.bizStates.add(bizState);
        return this;
    }

    public BizState getState(Integer stateId) {
        for (BizState bizState : bizStates) {
            if (bizState.getId().getId().equals(stateId)) {
                return bizState;
            }
        }
        return null;
    }

    public BizStateId parse(Integer stateId) {
        BizState bizState = getState(stateId);
        if (bizState != null) {
            return bizState.getId();
        }
        return null;
    }

    public List<BizAction> listAction() {
        return this.firstActions;
    }

    public static void disposeState(IState domain, BaseCommand command) {
        IStateMachine bizStateMachine = domain.getStateMachine();
        if (bizStateMachine == null) {
            throw new ApplicationException(ErrorCode.NoStateMachine);
        }
        if (domain.getStateId() == null) {
            List<BizAction> bizActions = bizStateMachine.getFirstAction();
            boolean canExecute = false;
            for (BizAction action : bizActions) {
                if (action.getCommand().equals(command.getCommandType())) {
                    domain.setStateId(action.getState().getId());
                    canExecute = true;
                    break;
                }
            }
            if (!canExecute) {
                throw new ApplicationException(ErrorCode.StateMachineExecuteFail);
            }
        } else {
            BizState state = bizStateMachine.getState(domain.getStateId());
            if (state == null) {
                throw new ApplicationException(ErrorCode.StateMachineExecuteFail);
            }
            BizAction bizAction = state.getAction(command);
            if (bizAction == null) {
                throw new ApplicationException(ErrorCode.StateMachineExecuteFail);
            } else {
                if (bizAction.getState() != null) {
                    domain.setStateId(bizAction.getState().getId());
                }
            }
        }
    }
}
