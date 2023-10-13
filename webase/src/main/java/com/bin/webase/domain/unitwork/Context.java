package com.bin.webase.domain.unitwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {
    private final List<DbAction> dbActions;
    private final Map<String, DbAction> idMap;

    public Context(){
        dbActions = new ArrayList<>();
        idMap = new HashMap<>();
    }

    public List<DbAction> getActions() {
        return dbActions;
    }

    public Map<String, DbAction> getIdMap() {
        return idMap;
    }

}
