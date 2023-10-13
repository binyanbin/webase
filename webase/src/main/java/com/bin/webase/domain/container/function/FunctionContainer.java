package com.bin.webase.domain.container.function;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunctionContainer {
    private final Map<Integer, Function> functionMap;
    private final List<Function> functions;

    public FunctionContainer(IListFunction iFunction) throws Exception {
        this.functionMap = new HashMap<>();
        this.functions = iFunction.list();
        for (Function function : functions) {
            if (functionMap.containsKey(function.getFunctionId())) {
                throw new Exception("初始化失败,有相同的权限标识");
            } else {
                functionMap.put(function.getFunctionId(), function);
            }
        }
    }

    public Function parse(Integer functionId) {
        return functionMap.get(functionId);
    }

    public boolean exists(Integer functionId) {
        return functionMap.containsKey(functionId);
    }

    public List<Function> parse(Integer[] functionIds) {
        List<Function> result = new ArrayList<>();
        for (Integer functionId : functionIds) {
            result.add(parse(functionId));
        }
        return result;
    }


    public List<Function> listFunction() {
        return functions;
    }

    public List<Integer> listFunctionId() {
        List<Integer> result = new ArrayList<>();
        for (Function function : functions) {
            result.add(function.getFunctionId());
        }
        return result;
    }
}
