package com.bin.api.dao.repository.mapper;

import com.bin.webase.core.model.IdName;
import com.bin.webase.exception.ApplicationException;
import com.bin.webase.exception.ErrorCode;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CommonSqlService {

    CommonMapper commonMapper;

    public CommonSqlService(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }


    public List<IdName<Long>> listName(List<Long> ids, String table) {
        if (CollectionUtils.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        return commonMapper.listName("id", "name", table, ids);
    }

    public Long getMaxId(String table) {
        if (StringUtils.hasText(table)) {
            return commonMapper.getMaxId(table);
        } else {
            throw new ApplicationException(ErrorCode.NullPointerException);
        }
    }
}
