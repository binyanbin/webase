package com.bin.api.dao.repository;

import com.bin.api.dao.mybatis.base.ClassTypeMapper;
import com.bin.api.dao.mybatis.model.ClassType;
import com.bin.api.dao.mybatis.model.ClassTypeExample;
import com.bin.api.dao.repository.mapper.CommonSqlService;
import com.bin.webase.core.context.DoRepository;
import com.bin.webase.core.context.IRepository;
import com.bin.webase.core.entity.statemachine.NormalStateMachine;
import org.assertj.core.util.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;

@DoRepository
public class ClassTypeRepository implements IRepository<ClassType> {
    private static final String TABLE_NAME = "bi_class_type";
    private final ClassTypeMapper classTypeMapper;
    private final CommonSqlService commonMapper;

    public ClassTypeRepository(ClassTypeMapper classTypeMapper, CommonSqlService commonMapper) {
        this.classTypeMapper = classTypeMapper;
        this.commonMapper = commonMapper;
    }

    public List<ClassType> listByBranchId(Long branchId, Integer status) {
        ClassTypeExample example = new ClassTypeExample();
        example.createCriteria().andBranchIdEqualTo(branchId).andStatusIdEqualTo(status);
        return classTypeMapper.selectByExample(example);
    }

    public List<ClassType> listByBranchId(Long branchId) {
        ClassTypeExample example = new ClassTypeExample();
        example.createCriteria().andBranchIdEqualTo(branchId).andStatusIdIn(NormalStateMachine.noDelete());
        return classTypeMapper.selectByExample(example);
    }

    public List<ClassType> listByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        ClassTypeExample example = new ClassTypeExample();
        example.createCriteria().andIdIn(ids);
        return classTypeMapper.selectByExample(example);
    }

    @Override
    public ClassType getModel(Long id) {
        return classTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(ClassType model) {
        classTypeMapper.insert(model);
    }

    @Override
    public void delete(Long id) {
        classTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ClassType model) {
        classTypeMapper.updateByPrimaryKey(model);
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
