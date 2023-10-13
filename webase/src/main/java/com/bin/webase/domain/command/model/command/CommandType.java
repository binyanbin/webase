package com.bin.webase.domain.command.model.command;

public class CommandType {
    private IdName<Integer> idName;

    CommandType(Integer id, String name) {
        this.idName = new IdName(id, name);
    }

    public static CommandType newC(Integer id, String name) {
        return new CommandType(id, name);
    }

    public Integer getId() {
        return idName.getId();
    }

    public String getName() {
        return idName.getName();
    }

    public static final CommandType NOOP = CommandType.newC(0, "无操作");
}
