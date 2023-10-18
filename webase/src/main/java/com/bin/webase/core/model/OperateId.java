package com.bin.webase.core.model;

import com.bin.webase.core.context.WeContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperateId {

    private static Map<Integer, OperateId> mapCommand = new HashMap<>();

    public static OperateId def(Integer id, String name) {
        OperateId result = new OperateId(id, name);
        if (!mapCommand.containsKey(id)) {
            mapCommand.put(id, result);
        } else {
            WeContext.error("command[" + id + "]已定义");
        }
        return result;
    }

    public static List<Integer> listId() {
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, OperateId> entry : mapCommand.entrySet()) {
            result.add(entry.getKey());
        }
        return result;
    }

    private IdName<Integer> idName;

    public static List<OperateId> listCommand() {
        List<OperateId> result = new ArrayList<>();
        for (Map.Entry<Integer, OperateId> entry : mapCommand.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }


    OperateId(Integer id, String name) {
        this.idName = new IdName(id, name);
    }

    public static OperateId parse(Integer id) {
        return mapCommand.get(id);
    }

    public Integer getId() {
        return idName.getId();
    }

    public String getName() {
        return idName.getName();
    }

    public static final OperateId NOOP = OperateId.def(0, "无操作");
}
