package com.bin.webase.core.operate;

import com.bin.webase.core.context.WebaseContext;
import com.bin.webase.core.context.IOperateLog;
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
        IOperateLog branchLog = WebaseContext.getOperateLog();
        if (branchLog != null) {
            WebaseContext.getOperateLog().log(operateId, domain, param, msg);
        }
    }

    protected void saveBranchLog(OperateId operateId, DbDomain domain, IParam param) {
        IOperateLog branchLog = WebaseContext.getOperateLog();
        if (branchLog != null) {
            WebaseContext.getOperateLog().log(operateId, domain, param, "");
        }
    }

    protected void saveBranchLog(OperateId operateId, DbDomain domain) {
        IOperateLog branchLog = WebaseContext.getOperateLog();
        if (branchLog != null) {
            WebaseContext.getOperateLog().log(operateId, domain, null, "");
        }
    }
}
