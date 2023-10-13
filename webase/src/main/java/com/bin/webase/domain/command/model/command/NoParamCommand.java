package com.bin.webase.domain.command.model.command;

public abstract class NoParamCommand extends BaseCommand {

    public NoParamCommand() {
        super();
    }

    @Override
    public String getInfo() {
        return "nothing";
    }
}
