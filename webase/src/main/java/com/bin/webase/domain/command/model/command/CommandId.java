package com.bin.webase.domain.command.model.command;

import com.bin.webase.domain.container.DomainRegistry;

import java.util.HashSet;
import java.util.Set;

public class CommandId {
    private IdName<Integer> idName;
    private static Set<Integer> commandIdSet = new HashSet<>();

    CommandId(Integer id, String name) {
        this.idName = new IdName(id, name);
    }

    public static CommandId newC(Integer id, String name) {
        if (!commandIdSet.contains(id)){
            commandIdSet.add(id);
        } else {
            DomainRegistry.error("command[" + id + "]已定义");
        }

        return new CommandId(id, name);
    }

    public Integer getId() {
        return idName.getId();
    }

    public String getName() {
        return idName.getName();
    }

    public static final CommandId NOOP = CommandId.newC(0, "无操作");
}
