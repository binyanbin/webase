package com.bin.webase.domain.command;


import com.bin.webase.domain.command.model.command.BaseCommand;

public abstract class BaseOperator extends BaseContainer {

    public abstract void commit(BaseCommand command);

}
