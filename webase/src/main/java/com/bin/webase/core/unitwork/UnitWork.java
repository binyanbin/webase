package com.bin.webase.core.unitwork;


import com.alibaba.fastjson.JSON;
import com.bin.webase.core.context.CacheDbRepository;
import com.bin.webase.core.context.ICacheRepository;
import com.bin.webase.core.context.ISequence;
import com.bin.webase.core.context.WebaseContext;
import com.bin.webase.core.entity.*;
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
        Context context = getContext();
        DbAction dbAction = getDbAction(domain);
        if (dbAction != null) {
            dbAction.getDbObject().setDbDomain(domain).setDbType(DbType.delete);
        } else {
            context.saveObject(domain, DbType.delete);
        }
        if (domain instanceof IDbCache) {
            String key = CacheDbRepository.PREFIX + domain.getModel().getClass().getTypeName() + domain.getId();
            any(() -> WebaseContext.getCacheBean().delete(key));
        }
        if (domain instanceof IBranch) {
            IBranch iBranch = (IBranch) domain;
            if (iBranch.branchCache()) {
                String key = CacheDbRepository.BRANCH_PREFIX + domain.getModel().getClass().getTypeName() + iBranch.getBranchId();
                any(() -> WebaseContext.getCacheBean().delete(key));
            }
        }
    }

    public void save(DbDomain domain) {
        Context context = getContext();
        if (domain.getId() == null) {
            domain.setId(sequence.newKey(domain));
            context.saveObject(domain, DbType.add);
        } else {
            DbAction dbAction = getDbAction(domain);
            if (dbAction != null) {
                dbAction.getDbObject().setDbDomain(domain).setDbType(DbType.update);
            } else {
                context.saveObject(domain, DbType.update);
            }
        }
        if (domain instanceof IDbCache) {
            IDbCache cache = (IDbCache) domain;
            String key = CacheDbRepository.PREFIX + domain.getModel().getClass().getTypeName() + domain.getId();
            any(() -> WebaseContext.getCacheBean().set(key, JSON.toJSONString(domain.getModel()), cache.getCacheSecond()));
        }
        if (domain instanceof IBranch) {
            IBranch iBranch = (IBranch) domain;
            if (iBranch.branchCache()) {
                String key = CacheDbRepository.BRANCH_PREFIX + domain.getModel().getClass().getTypeName() + iBranch.getBranchId();
                any(() -> WebaseContext.getCacheBean().delete(key));
            }
        }
    }

    public void save(CacheDomain domain) {
        ErrorCheck.checkNotNull(domain.getUniqueId(), "id不存在");
        getContext().saveDomain(domain, DomainType.save);
    }

    public void remove(CacheDomain domain) {
        ErrorCheck.checkNotNull(domain.getUniqueId(), "id不存在");
        getContext().saveDomain(domain, DomainType.save);
    }

    public void any(Runner runner) {
        getContext().saveRunner(runner);
    }

    public void saveAfterRun(Runner runner) {
        getContext().addAfter(runner);
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
        ICacheRepository cache = WebaseContext.getCacheBean();
        for (DomainObject domainObject : domains) {
            CacheDomain domain = domainObject.getDomain();
            if (domainObject.getType() == DomainType.save) {
                cache.set(domain.getUniqueId(), domain.toJson());
            } else if (domainObject.getType() == DomainType.remove) {
                cache.delete(domain.getUniqueId());
            }
        }
        for (Runner runner : runners) {
            runner.run();
        }
        List<Runner> runnerList = getContext().getAfterRun();
        for (Runner runner : runnerList) {
            runner.run();
        }
        context.remove();
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