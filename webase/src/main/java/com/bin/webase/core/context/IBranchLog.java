package com.bin.webase.core.context;


import com.bin.webase.core.operate.IParam;
import com.bin.webase.core.operate.OperateId;
import com.bin.webase.core.entity.DbDomain;

public interface IBranchLog {
    DbDomain newBranchLog(OperateId command, DbDomain domain, IParam param, String msg);
}
