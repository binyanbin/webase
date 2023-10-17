package com.bin.api.dao.repository;

import com.bin.api.dao.mybatis.base.CampusMapper;
import com.bin.api.dao.mybatis.model.Campus;
import com.bin.api.dao.mybatis.model.CampusExample;
import com.bin.api.dao.repository.mapper.CommonSqlService;
import com.bin.webase.core.context.DoRepository;
import com.bin.webase.core.context.IRepository;
import com.bin.webase.core.entity.statemachine.NormalStateMachine;
import org.assertj.core.util.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;

@DoRepository
public class CampusRepository implements IRepository<Campus> {
    private static final String TABLE_NAME = "bi_campus";
    private final CampusMapper campusMapper;
    private final CommonSqlService commonMapper;

    public List<Campus> listByBranchId(Long branchId, Integer status) {
        CampusExample example = new CampusExample();
        example.createCriteria().andBranchIdEqualTo(branchId).andStatusIdEqualTo(status);
        return campusMapper.selectByExample(example);
    }

    public List<Campus> listByBranchId(Long branchId) {
        CampusExample example = new CampusExample();
        example.createCriteria().andBranchIdEqualTo(branchId).andStatusIdIn(NormalStateMachine.noDelete());
        return campusMapper.selectByExample(example);
    }

    public List<Campus> listByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)){
            return Lists.newArrayList();
        }
        CampusExample example = new CampusExample();
        example.createCriteria().andIdIn(ids);
        return campusMapper.selectByExample(example);
    }

    public CampusRepository(CampusMapper campusMapper, CommonSqlService commonMapper) {
        this.campusMapper = campusMapper;
        this.commonMapper = commonMapper;
    }

    @Override
    public Campus getModel(Long id) {
        return campusMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Campus model) {
        campusMapper.insert(model);
    }

    @Override
    public void delete(Long id) {
        campusMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Campus model) {
        campusMapper.updateByPrimaryKey(model);
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
