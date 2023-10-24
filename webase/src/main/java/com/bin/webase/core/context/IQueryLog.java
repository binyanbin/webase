package com.bin.webase.core.context;

import com.bin.webase.core.entity.DbDomain;
import com.bin.webase.core.model.IParam;
import com.bin.webase.core.model.QueryId;

public interface IQueryLog {
    DbDomain log(QueryId queryId, IParam param);
}
