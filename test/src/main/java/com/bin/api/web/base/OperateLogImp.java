package com.bin.api.web.base;

import com.bin.api.operate.domain.db.BranchLogDo;

import com.bin.webase.core.context.IOperateLog;
import com.bin.webase.core.entity.DbDomain;
import com.bin.webase.core.model.IParam;
import com.bin.webase.core.model.OperateId;
import org.springframework.stereotype.Service;

@Service
public class OperateLogImp implements IOperateLog {
    @Override
    public DbDomain log(OperateId command, DbDomain domain, IParam param, String msg) {
        return BranchLogDo.newInstance(command, domain, param, msg);
    }
}

