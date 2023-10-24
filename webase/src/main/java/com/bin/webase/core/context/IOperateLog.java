package com.bin.webase.core.context;


import com.bin.webase.core.model.IParam;
import com.bin.webase.core.model.OperateId;
import com.bin.webase.core.entity.DbDomain;

public interface IOperateLog {
    DbDomain log(OperateId command, DbDomain domain, IParam param, String msg);
}
