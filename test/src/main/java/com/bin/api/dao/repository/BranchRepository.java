package com.bin.api.dao.repository;


import com.bin.api.dao.mybatis.base.BranchMapper;
import com.bin.api.dao.mybatis.model.Branch;
import com.bin.api.dao.repository.mapper.CommonSqlService;
import com.bin.webase.core.model.IdName;
import com.bin.webase.core.context.DoRepository;
import com.bin.webase.core.context.IRepository;

import java.util.List;

@DoRepository
public class BranchRepository implements IRepository<Branch> {
    private static final String TABLE_NAME = "pl_branch";
    private final BranchMapper mapper;
    private final CommonSqlService commonMapper;

    public BranchRepository(BranchMapper mapper, CommonSqlService commonMapper) {
        this.mapper = mapper;
        this.commonMapper = commonMapper;
    }

    @Override
    public Branch getModel(Long id) {
        return mapper.selectByPrimaryKey(id);
    }


    @Override
    public void add(Branch model) {
        mapper.insert(model);
    }

    @Override
    public void delete(Long id) {
        mapper.deleteByPrimaryKey(id);
    }


    @Override
    public void update(Branch model) {
        mapper.updateByPrimaryKey(model);
    }

    @Override
    public Long getMaxId() {
        return commonMapper.getMaxId(TABLE_NAME);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    public List<IdName<Long>> listByIds(List<Long> ids) {
        return commonMapper.listName(ids,getTableName());
    }

}
