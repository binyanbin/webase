package com.bin.webase.domain.unitwork;


import com.bin.webase.domain.container.DomainRegistry;
import com.bin.webase.domain.container.ICache;
import com.bin.webase.domain.container.ISequence;
import com.bin.webase.domain.entity.CacheDomain;
import com.bin.webase.domain.entity.DbDomain;
import com.bin.webase.domain.entity.UniqueId;
import com.bin.webase.exception.ErrorCheck;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 工作单元
 */
public class UnitWork {
    private static final ThreadLocal<Context> context = new ThreadLocal<>();
    private final ISequence sequence;

    public UnitWork(ISequence sequence) {
        this.sequence = sequence;
    }

    private Context getContext() {
        Context ctx = context.get();
        if (ctx == null) {
            ctx = new Context();
            context.set(ctx);
        }
        return ctx;
    }

    public void remove(DbDomain domain) {
        ErrorCheck.checkNotNull(domain.getId(), "id不存在");
        DbAction dbAction = getDbAction(domain);
        if (dbAction != null) {
            dbAction.getDbObject().setDbDomain(domain).setDbType(DbType.delete);
        } else {
            saveObject(domain, DbType.delete);
        }
    }

    public void save(DbDomain domain) {
        if (domain.getId() == null) {
            domain.setId(sequence.newKey(domain));
            saveObject(domain, DbType.add);
        } else {
            DbAction dbAction = getDbAction(domain);
            if (dbAction != null) {
                dbAction.getDbObject().setDbDomain(domain).setDbType(DbType.update);
            } else {
                saveObject(domain, DbType.update);
            }
        }
    }

    public void save(CacheDomain domain) {
        ErrorCheck.checkNotNull(domain.getUniqueId(), "id不存在");
        saveDomain(domain, DomainType.save);
    }

    public void remove(CacheDomain domain) {
        ErrorCheck.checkNotNull(domain.getUniqueId(), "id不存在");
        saveDomain(domain, DomainType.remove);
    }

    public void any(Runner runner) {
        saveRunner(runner);
    }

    public void commit() {
        List<DbAction> objects = getContext().getActions();
        List<DomainObject> domains = new ArrayList<>();
        List<Runner> runners = new ArrayList<>();
        for (DbAction o : objects) {
            DbObject dbObject = o.getDbObject();
            DomainObject domain = o.getDomain();
            Runner runner = o.getRunner();
            if (dbObject != null) {
                DbDomain dbDomain = dbObject.getDbDomain();
                if (dbObject.getDbType().equals(DbType.add)) {
                    dbDomain.getRepository().add(dbDomain.getModel());
                } else if (dbObject.getDbType().equals(DbType.update)) {
                    dbDomain.getRepository().update(dbDomain.getModel());
                } else if (dbObject.getDbType().equals(DbType.delete)) {
                    dbDomain.getRepository().delete(dbDomain.getId());
                }
            }
            if (runner != null) {
                runners.add(runner);
            }
            if (domain != null) {
                domains.add(domain);
            }
        }
        ICache cache = DomainRegistry.getCacheBean();
        for (DomainObject domainObject : domains) {
            CacheDomain domain = domainObject.getDomain();;
            if (domainObject.getType() == DomainType.save) {
                cache.set(domain.getUniqueId(),domain.toJson());
            } else if (domainObject.getType() == DomainType.remove) {
                cache.delete(domain.getUniqueId());
            }
        }
        for (Runner runner : runners) {
            runner.run();
        }
        context.remove();
    }

    private void saveObject(DbDomain o, DbType dbType) {
        List<DbAction> list = getContext().getActions();
        DbAction dbAction = new DbAction(new DbObject(o, dbType), null, null);
        list.add(dbAction);

        Map<String, DbAction> mapId = getContext().getIdMap();
        mapId.put(o.getUniqueId(), dbAction);
    }

    private void saveRunner(Runner runner) {
        List<DbAction> list = getContext().getActions();
        list.add(new DbAction(null, runner, null));
    }

    private void saveDomain(CacheDomain domain, DomainType type) {
        DbAction dbAction = getDbAction(domain);
        if (dbAction != null) {
            dbAction.getDomain().setDomain(domain).setType(type);
        } else {
            List<DbAction> list = getContext().getActions();
            dbAction = new DbAction(null, null, new DomainObject(type, domain));
            list.add(dbAction);
            Map<String, DbAction> mapId = getContext().getIdMap();
            mapId.put(domain.getUniqueId(), dbAction);
        }
    }

    public <T extends DbDomain> T getDbDomain(String uniqueId) {
        Map<String, DbAction> mapId = getContext().getIdMap();
        DbAction dbAction = mapId.get(uniqueId);
        if (dbAction == null || dbAction.getDbObject() == null) {
            return null;
        }
        return (T) dbAction.getDbObject().getDbDomain();
    }

    public <T extends CacheDomain> T getBaseDomain(String uniqueId) {
        Map<String, DbAction> mapId = getContext().getIdMap();
        DbAction dbAction = mapId.get(uniqueId);
        if (dbAction == null || dbAction.getDomain() == null) {
            return null;
        }
        return (T) dbAction.getDomain().getDomain();
    }

    public DbObject getDbObject(String uniqueId) {
        Map<String, DbAction> mapId = getContext().getIdMap();
        DbAction dbAction = mapId.get(uniqueId);
        if (dbAction == null || dbAction.getDbObject() == null) {
            return null;
        }
        return dbAction.getDbObject();
    }

    public <T extends UniqueId> DbAction getDbAction(T domain) {
        Map<String, DbAction> mapId = getContext().getIdMap();
        return mapId.get(domain.getUniqueId());
    }
}