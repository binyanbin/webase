package com.bin.webase.domain.operate;


import com.bin.webase.domain.container.Container;
import com.bin.webase.domain.unitwork.UnitWork;

public class OperateHolder {

    private final UnitWork unitWork;
    private final AfterCommandHandler afterCommit;

    OperateHolder() {
        this.unitWork = new UnitWork(Container.getSequenceBean());
        this.afterCommit = new AfterCommandHandler();
    }

    static OperateHolder instance() {
        return Holder.INSTANCE;
    }


    UnitWork getUnitWork() {
        return unitWork;
    }

    AfterCommandHandler getAfterCommandHandler() {
        return afterCommit;
    }

    private static class Holder {
        private static final OperateHolder INSTANCE = new OperateHolder();
    }
}
