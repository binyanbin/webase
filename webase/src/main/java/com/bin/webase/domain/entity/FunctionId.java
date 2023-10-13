package com.bin.webase.domain.entity;

import com.bin.webase.domain.command.model.command.IdName;
import com.bin.webase.domain.container.DomainRegistry;

import java.util.HashSet;
import java.util.Set;

public class FunctionId {
    private IdName<Integer> idName;
    private static Set<Integer> functionIdSet = new HashSet<>();

    public FunctionId(Integer id, String name) {
        this.idName = new IdName<>(id, name);
    }

    public static FunctionId newF(Integer id, String name) {
        if (!functionIdSet.contains(id)) {
            functionIdSet.add(id);
        } else {
            DomainRegistry.error("Function[" + id + "]已定义");
        }
        return new FunctionId(id, name);
    }

    public static Set<Integer> listFunctionId() {
        return functionIdSet;
    }

    public Integer getId() {
        return idName.getId();
    }

    public Integer getFunctionId() {
        return idName.getId();
    }

    public String getName() {
        return idName.getName();
    }

}
