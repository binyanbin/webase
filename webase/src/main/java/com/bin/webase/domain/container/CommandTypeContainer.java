package com.bin.webase.domain.container;

import com.bin.webase.domain.command.model.command.CommandType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandTypeContainer {

    private final Map<Integer, CommandType> commandMap;
    private final List<CommandType> commands;

    public CommandTypeContainer(IListCommand iCommand) throws Exception {
        commandMap = new HashMap<>();
        commands = iCommand.list();
        if (commands == null || commands.size() == 0) {
            throw new Exception("没有找到命令集合");
        }
        for (CommandType commandType : commands) {
            if (commandMap.containsKey(commandType.getId())) {
                throw new Exception("初始化失败,有相同的命令");
            }
            commandMap.put(commandType.getId(), commandType);
        }
    }

    public CommandType parse(Integer commandId) {
        return commandMap.get(commandId);
    }

    public boolean exists(Integer commandId) {
        return commandMap.containsKey(commandId);
    }

    public List<CommandType> listCommandType() {
        return commands;
    }
}
