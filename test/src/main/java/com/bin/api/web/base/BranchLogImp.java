package com.bin.api.web.base;

import com.bin.api.operate.domain.db.BranchLogDo;

import com.bin.webase.core.context.IBranchLog;
import com.bin.webase.core.entity.DbDomain;
import com.bin.webase.core.operate.IParam;
import com.bin.webase.core.operate.OperateId;
import org.springframework.stereotype.Service;

@Service
public class BranchLogImp implements IBranchLog {
    @Override
    public DbDomain newBranchLog(OperateId command, DbDomain domain, IParam param, String msg) {
        return BranchLogDo.newInstance(command, domain, param, msg);
    }
}

