package com.bin.webase.domain.entity;

import com.bin.webase.domain.command.model.command.IdName;
import com.bin.webase.domain.container.DomainRegistry;

import java.util.*;

public class FunctionId {

    private static Map<Integer, FunctionId> mapFunctionId = new HashMap<>();

    public static FunctionId def(Integer id, String name) {
        FunctionId result = new FunctionId(id, name);
        if (!mapFunctionId.containsKey(id)) {
            mapFunctionId.put(id, result);
        } else {
            DomainRegistry.error("Function[" + id + "]已定义");
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
