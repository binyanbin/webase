package com.bin.webase.core.model;

import com.bin.webase.core.context.WebaseContext;
import com.bin.webase.core.web.ApiToken;

import java.util.*;

public class FunctionId {

    private static Map<Integer, FunctionId> mapFunctionId = new HashMap<>();

    public static FunctionId def(Integer id, String name) {
        FunctionId result = new FunctionId(id, name);
        if (!mapFunctionId.containsKey(id)) {
            mapFunctionId.put(id, result);
        } else {
            WebaseContext.error("Function[" + id + "]已定义");
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

    public static boolean validate(ApiToken token, List<FunctionId> functionIds) {
        if (token != null) {
            for (FunctionId functionId : functionIds) {
                if (token.validFunction(functionId)) {
                    return true;
                }
            }
        }
        return false;
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

    public static final FunctionId ERROR_FUNCTION = new FunctionId(0, "测试无权限");

}
