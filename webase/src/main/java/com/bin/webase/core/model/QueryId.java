package com.bin.webase.core.model;

import com.bin.webase.core.context.WeContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryId {
    private static Map<Integer, QueryId> mapQuery = new HashMap<>();

    public static QueryId def(Integer id, String name) {
        QueryId result = new QueryId(id, name);
        if (!mapQuery.containsKey(id)) {
            mapQuery.put(id, result);
        } else {
            WeContext.error("query[" + id + "]已定义");
        }
        return result;
    }

    public static List<Integer> listId() {
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, QueryId> entry : mapQuery.entrySet()) {
            result.add(entry.getKey());
        }
        return result;
    }

    private IdName<Integer> idName;

    public static List<QueryId> listCommand() {
        List<QueryId> result = new ArrayList<>();
        for (Map.Entry<Integer, QueryId> entry : mapQuery.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }


    QueryId(Integer id, String name) {
        this.idName = new IdName(id, name);
    }

    public static QueryId parse(Integer id) {
        return mapQuery.get(id);
    }

    public Integer getId() {
        return idName.getId();
    }

    public String getName() {
        return idName.getName();
    }

    public static final QueryId NOOP = QueryId.def(0, "无操作");
}
