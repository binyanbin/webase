package com.bin.webase.core.entity.statemachine;

public interface IState {

    Integer getStateId();

    void setStateId(Integer stateId);

    IStateMachine getStateMachine();

}
