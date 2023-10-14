package com.bin.webase.domain.command.model.command;

import com.bin.webase.domain.container.DomainRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandId {

    private static Map<Integer, CommandId> mapCommand = new HashMap<>();

    public static CommandId def(Integer id, String name) {
        CommandId result = new CommandId(id, name);
        if (!mapCommand.containsKey(id)) {
            mapCommand.put(id, result);
        } else {
            DomainRegistry.error("command[" + id + "]已定义");
        }
        return result;
    }

    public static List<Integer> listId() {
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, CommandId> entry : mapCommand.entrySet()) {
            result.add(entry.getKey());
        }
        return result;
    }

    private IdName<Integer> idName;

    public static List<CommandId> listCommand() {
        List<CommandId> result = new ArrayList<>();
        for (Map.Entry<Integer, CommandId> entry : mapCommand.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }


    CommandId(Integer id, String name) {
        this.idName = new IdName(id, name);
    }

    public static CommandId parse(Integer id) {
        return mapCommand.get(id);
    }

    public Integer getId() {
        return idName.getId();
    }

    public String getName() {
        return idName.getName();
    }

    public static final CommandId NOOP = CommandId.def(0, "无操作");
}
