package webase.domain.command;

import com.bin.webase.domain.entity.CacheDomain;
import com.bin.webase.domain.entity.DbDomain;
import com.bin.webase.domain.unitwork.Runner;

/**
 * 执行容器
 */
class BaseContainer {
    public void save(DbDomain domain) {
        UnitWorkUtils.save(domain);
    }

    public void save(CacheDomain domain) {
        UnitWorkUtils.save(domain);
    }

    public void remove(CacheDomain domain) {
        UnitWorkUtils.remove(domain);
    }

    public void remove(DbDomain domain) {
        UnitWorkUtils.remove(domain);
    }

    public void any(Runner runner) {
        UnitWorkUtils.any(runner);
    }

    public void afterCommit(Runner runner) {
        UnitWorkUtils.afterCommand(runner);
    }
}
