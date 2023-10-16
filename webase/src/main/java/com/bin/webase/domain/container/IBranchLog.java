package com.bin.webase.domain.container;


import com.bin.webase.domain.operate.model.IParam;
import com.bin.webase.domain.operate.model.OperateId;
import com.bin.webase.domain.entity.DbDomain;

public interface IBranchLog {
    DbDomain newBranchLog(OperateId command, DbDomain domain, IParam param, String msg);
}
