package com.bin.api.web.base;

import com.bin.webase.core.context.IQueryLog;
import com.bin.webase.core.entity.DbDomain;
import com.bin.webase.core.model.IParam;
import com.bin.webase.core.model.QueryId;
import org.springframework.stereotype.Service;

@Service
public class QueryLogImp implements IQueryLog {
    @Override
    public DbDomain log(QueryId queryId, IParam param) {
        return null;
    }
}
