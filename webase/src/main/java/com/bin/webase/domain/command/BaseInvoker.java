package com.bin.webase.domain.command;

import com.bin.webase.domain.command.model.command.BaseCommand;
import com.bin.webase.domain.unitwork.CommitResult;

public abstract class BaseInvoker {

    protected CommitResult run(BaseCommand command) {
        return Invoker.instance().execute(command);
    }

    public abstract CommitResult execute(BaseCommand command);

}
