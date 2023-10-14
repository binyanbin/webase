package com.bin.webase.domain.command;


import com.bin.webase.domain.command.model.command.BaseCommand;
import com.bin.webase.domain.container.DomainRegistry;
import com.bin.webase.domain.unitwork.CommitResult;
import com.bin.webase.domain.unitwork.UnitWork;
import com.bin.webase.exception.ErrorCheck;
import com.bin.webase.exception.ErrorCode;

public class Invoker {

    private final UnitWork unitWork;
    private final AfterCommandHandler afterCommit;

    Invoker() {
        this.unitWork = new UnitWork(DomainRegistry.getSequenceBean());
        this.afterCommit = new AfterCommandHandler();
    }

    static Invoker instance() {
        return Holder.INSTANCE;
    }


    public CommitResult execute(BaseCommand command) {
        beforeCommit(command);
        if (command.getResult().getState() != ResultState.fail) {
            unitWork.commit();
            afterCommit.execute();
            return CommitResult.SUCCESS;
        } else {
            return new CommitResult(command.getResult().getMsg());
        }
    }

    private void beforeCommit(BaseCommand command) {
        if (command != null) {
            BaseReceiver baseReceiver = command.getReceiver();
            ErrorCheck.checkNotNull(baseReceiver, ErrorCode.NotFoundCommandReceiver);
            baseReceiver.handler(command);
        }
    }

    UnitWork getUnitWork() {
        return unitWork;
    }

    AfterCommandHandler getAfterCommandHandler() {
        return afterCommit;
    }

    private static class Holder {
        private static final Invoker INSTANCE = new Invoker();
    }
}
