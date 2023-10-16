package com.bin.webase.core.entity.statemachine;


import java.util.List;

public interface IStateMachine {

    BizStateId parse(Integer stateId);

    BizState getState(Integer stateId);

    List<BizAction> getFirstAction();

}
