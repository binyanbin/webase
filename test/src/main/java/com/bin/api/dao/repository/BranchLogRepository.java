package com.bin.api.dao.repository;


import com.bin.api.dao.mybatis.base.BranchLogMapper;
import com.bin.api.dao.mybatis.model.BranchLog;
import com.bin.api.dao.mybatis.model.BranchLogExample;
import com.bin.api.dao.repository.mapper.CommonSqlService;

import com.bin.webase.core.context.DoRepository;
import com.bin.webase.core.context.IRepository;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.Date;
import java.util.List;

@DoRepository
public class BranchLogRepository implements IRepository<BranchLog> {
    private static final String TABLE_NAME = "pl_branch_log";
    BranchLogMapper mapper;
    CommonSqlService commonMapper;

    public BranchLogRepository(BranchLogMapper branchLogMapper, CommonSqlService commonMapper) {
        this.mapper = branchLogMapper;
        this.commonMapper = commonMapper;
    }

    public Page<BranchLog> listByBranchId(Long branchId, Date begin, Date end, Integer pageIndex, Integer pageSize) {
        Page page = PageHelper.startPage(pageIndex, pageSize);
        BranchLogExample example = new BranchLogExample();
        example.createCriteria().andBranchIdEqualTo(branchId).andCreatedTimeBetween(begin, end);
        mapper.selectByExample(example);
        return page;
    }

    public List<BranchLog> listByLogId(String logId) {
        BranchLogExample example = new BranchLogExample();
        example.createCriteria().andLogIdEqualTo(logId);
        return mapper.selectByExample(example);
    }


    @Override
    public BranchLog getModel(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(BranchLog model) {
        mapper.insert(model);
    }

    @Override
    public void delete(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(BranchLog model) {
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
}