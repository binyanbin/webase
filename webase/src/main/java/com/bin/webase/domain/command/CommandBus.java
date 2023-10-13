package com.bin.webase.domain.command;


import com.bin.webase.domain.command.model.command.BaseCommand;
import com.bin.webase.domain.unitwork.CommitResult;

/**
 * 对外调用
 */
public class CommandBus {

    public static CommitResult execute(BaseCommand command) throws Exception {
        return Invoker.instance().execute(command);
    }
}
