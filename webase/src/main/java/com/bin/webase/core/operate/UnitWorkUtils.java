package com.bin.webase.core.operate;


import com.bin.webase.core.context.WebaseContext;
import com.bin.webase.core.entity.CacheDomain;
import com.bin.webase.core.entity.DbDomain;
import com.bin.webase.core.unitwork.DbObject;
import com.bin.webase.core.unitwork.DbType;
import com.bin.webase.core.unitwork.Runner;

import java.util.List;
import java.util.function.Function;

/**
 * 工作单元对外接口
 */
public class UnitWorkUtils {

    public static <T extends DbDomain> T getDbObject(String uniqueId) {
        return WebaseContext.getUnitWork().getDbDomain(uniqueId);
    }

    public static <T extends CacheDomain> T getDomain(String uniqueId) {
        return WebaseContext.getUnitWork().getBaseDomain(uniqueId);
    }

    public static <T> List<T> updateListByCache(List<T> list, Function<T, Long> function) {
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            DbObject dbObject = WebaseContext.getUnitWork().getDbObject(DbDomain.getUniqueId(t.getClass(), function.apply(t)));
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
        WebaseContext.getUnitWork().save(domain);
    }

    static void save(CacheDomain domain) {
        if (domain == null || domain.isNull()) {
            throw new NullPointerException();
        }
        WebaseContext.getUnitWork().save(domain);
    }

    static void remove(CacheDomain domain) {
        if (domain != null && !domain.isNull()) {
            WebaseContext.getUnitWork().remove(domain);
        }
    }

    static void remove(DbDomain domain) {
        if (domain != null && !domain.isNull()) {
            WebaseContext.getUnitWork().remove(domain);
        }
    }

    static void any(Runner runner) {
        WebaseContext.getUnitWork().any(runner);
    }

    static void after(Runnable runner) {
        WebaseContext.getUnitWork().saveAfterRun(runner);
    }

    static void commit() {
        WebaseContext.getUnitWork().commit();
    }
}
