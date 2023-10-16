package com.bin.webase.domain.unitwork;

import com.bin.webase.domain.entity.CacheDomain;
import com.bin.webase.domain.entity.DbDomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {
    private final List<DbAction> dbActions;
    private final Map<String, DbAction> idMap;
    private final List<Runner> finishes;

    public Context() {
        dbActions = new ArrayList<>();
        idMap = new HashMap<>();
        finishes = new ArrayList<>();
    }

    public List<DbAction> getActions() {
        return dbActions;
    }

    public void saveObject(DbDomain o, DbType dbType) {
        DbAction dbAction = new DbAction(new DbObject(o, dbType), null, null);
        dbActions.add(dbAction);
        idMap.put(o.getUniqueId(), dbAction);
    }

    public void saveRunner(Runner runner) {
        dbActions.add(new DbAction(null, runner, null));
    }

    public void saveDomain(CacheDomain domain, DomainType type) {
        DbAction dbAction = idMap.get(domain.getUniqueId());
        if (dbAction != null) {
            dbAction.getDomain().setDomain(domain).setType(type);
        } else {
            dbAction = new DbAction(null, null, new DomainObject(type, domain));
            dbActions.add(dbAction);
            idMap.put(domain.getUniqueId(), dbAction);
        }
    }

    public Map<String, DbAction> getIdMap() {
        return idMap;
    }


    public void addAfter(Runner runner) {
        if (runner == null) {
            return;
        }
        finishes.add(runner);
    }

    public List<Runner> getAfterRun() {
        return finishes;
    }

}
