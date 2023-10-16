package com.bin.webase.domain.operate;


import com.bin.webase.domain.container.Container;
import com.bin.webase.domain.entity.CacheDomain;
import com.bin.webase.domain.entity.DbDomain;
import com.bin.webase.domain.unitwork.DbObject;
import com.bin.webase.domain.unitwork.DbType;
import com.bin.webase.domain.unitwork.Runner;
import com.bin.webase.domain.unitwork.UnitWork;

import java.util.List;
import java.util.function.Function;

/**
 * 工作单元对外接口
 */
public class UnitWorkUtils {

    private static final UnitWork UNIT_WORK = new UnitWork(Container.getSequenceBean());

    public static <T extends DbDomain> T getDbObject(String uniqueId) {
        return UNIT_WORK.getDbDomain(uniqueId);
    }

    public static <T extends CacheDomain> T getDomain(String uniqueId) {
        return UNIT_WORK.getBaseDomain(uniqueId);
    }

    public static <T> List<T> updateListByCache(List<T> list, Function<T, Long> function) {
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            DbObject dbObject = UNIT_WORK.getDbObject(DbDomain.getUniqueId(t.getClass(), function.apply(t)));
            if (dbObject != null) {
                if (dbObject.getDbType().equals(DbType.update)) {
                    DbDomain domain = dbObject.getDbDomain();
                    if (domain != null && !domain.isNull()) {
                        list.set(i, (T) domain.getModel());
                    }
                } else if (dbObject.getDbDomain().equals(DbType.delete)) {
                    list.remove(i--);
                }
            }
        }
        return list;
    }

    static void save(DbDomain domain) {
        if (domain == null || domain.isNull()) {
            throw new NullPointerException();
        }
        UNIT_WORK.save(domain);
    }

    static void save(CacheDomain domain) {
        if (domain == null || domain.isNull()) {
            throw new NullPointerException();
        }
        UNIT_WORK.save(domain);
    }

    static void remove(CacheDomain domain) {
        if (domain != null && !domain.isNull()) {
            UNIT_WORK.remove(domain);
        }
    }

    static void remove(DbDomain domain) {
        if (domain != null && !domain.isNull()) {
            UNIT_WORK.remove(domain);
        }
    }

    static void any(Runner runner) {
        UNIT_WORK.any(runner);
    }

    static void after(Runner runner) {
        UNIT_WORK.saveAfterRun(runner);
    }

    static void commit() {
        UNIT_WORK.commit();
    }
}
