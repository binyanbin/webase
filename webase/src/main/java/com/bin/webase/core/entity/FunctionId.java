package com.bin.webase.core.entity;

import com.bin.webase.core.model.IdName;
import com.bin.webase.core.context.WeContext;

import java.util.*;

public class FunctionId {

    private static Map<Integer, FunctionId> mapFunctionId = new HashMap<>();

    public static FunctionId def(Integer id, String name) {
        FunctionId result = new FunctionId(id, name);
        if (!mapFunctionId.containsKey(id)) {
            mapFunctionId.put(id, result);
        } else {
            WeContext.error("Function[" + id + "]已定义");
        }
        return result;
    }

    public static Set<Integer> listId() {
        Set<Integer> result = new HashSet<>();
        for (Map.Entry<Integer, FunctionId> entry : mapFunctionId.entrySet()) {
            result.add(entry.getKey());
        }
        return result;
    }

    public static List<FunctionId> listFunction() {
        List<FunctionId> result = new ArrayList<>();
        for (Map.Entry<Integer, FunctionId> entry : mapFunctionId.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    public static FunctionId parse(Integer id) {
        return mapFunctionId.get(id);
    }


    private IdName<Integer> idName;

    FunctionId(Integer id, String name) {
        this.idName = new IdName<>(id, name);
    }

    public Integer getId() {
        return idName.getId();
    }

    public String getName() {
        return idName.getName();
    }

}
