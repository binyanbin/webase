package com.bin.webase.domain.operate;

import com.bin.webase.domain.operate.model.IParam;
import com.bin.webase.domain.operate.model.OperateId;
import com.bin.webase.domain.container.Container;
import com.bin.webase.domain.container.IBranchLog;
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
        UnitWorkUtils.after(runner);
    }

    protected void saveBranchLog(OperateId operateId, String msg, IParam param, DbDomain domain) {
        IBranchLog branchLog = Container.getBranchLog();
        if (branchLog != null) {
            Container.getBranchLog().newBranchLog(operateId, domain, param, msg);
        }
    }

    protected void saveBranchLog(OperateId operateId, DbDomain domain, IParam param) {
        IBranchLog branchLog = Container.getBranchLog();
        if (branchLog != null) {
            Container.getBranchLog().newBranchLog(operateId, domain, param, "");
        }
    }

    protected void saveBranchLog(OperateId operateId, DbDomain domain) {
        IBranchLog branchLog = Container.getBranchLog();
        if (branchLog != null) {
            Container.getBranchLog().newBranchLog(operateId, domain, null, "");
        }
    }
}
