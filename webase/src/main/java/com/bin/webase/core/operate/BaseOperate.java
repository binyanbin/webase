package com.bin.webase.core.operate;

import com.bin.webase.core.context.WeContext;
import com.bin.webase.core.context.IBranchLog;
import com.bin.webase.core.entity.CacheDomain;
import com.bin.webase.core.entity.DbDomain;
import com.bin.webase.core.model.FunctionObject;
import com.bin.webase.core.model.IParam;
import com.bin.webase.core.model.OperateId;
import com.bin.webase.core.unitwork.Runner;

/**
 * 执行容器
 */
class BaseOperate extends FunctionObject {
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
        IBranchLog branchLog = WeContext.getBranchLog();
        if (branchLog != null) {
            WeContext.getBranchLog().newBranchLog(operateId, domain, param, msg);
        }
    }

    protected void saveBranchLog(OperateId operateId, DbDomain domain, IParam param) {
        IBranchLog branchLog = WeContext.getBranchLog();
        if (branchLog != null) {
            WeContext.getBranchLog().newBranchLog(operateId, domain, param, "");
        }
    }

    protected void saveBranchLog(OperateId operateId, DbDomain domain) {
        IBranchLog branchLog = WeContext.getBranchLog();
        if (branchLog != null) {
            WeContext.getBranchLog().newBranchLog(operateId, domain, null, "");
        }
    }
}
