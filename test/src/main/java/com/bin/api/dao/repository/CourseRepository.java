package com.bin.api.dao.repository;

import com.bin.api.dao.mybatis.base.CourseMapper;
import com.bin.api.dao.mybatis.model.Course;
import com.bin.api.dao.mybatis.model.CourseExample;
import com.bin.webase.core.context.DoRepository;
import com.bin.webase.core.context.IRepository;
import com.bin.webase.core.entity.statemachine.NormalStateMachine;
import com.bin.api.dao.repository.mapper.CommonSqlService;
import org.assertj.core.util.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;

@DoRepository
public class CourseRepository implements IRepository<Course> {
    private static final String TABLE_NAME = "bi_course";
    private final CourseMapper courseMapper;
    private final CommonSqlService commonMapper;

    public CourseRepository(CourseMapper courseMapper, CommonSqlService commonMapper) {
        this.courseMapper = courseMapper;
        this.commonMapper = commonMapper;
    }

    public List<Course> listByBranchId(Long branchId, Integer status) {
        CourseExample example = new CourseExample();
        example.createCriteria().andBranchIdEqualTo(branchId).andStatusIdEqualTo(status);
        return courseMapper.selectByExample(example);
    }

    public List<Course> listByBranchId(Long branchId) {
        CourseExample example = new CourseExample();
        example.createCriteria().andBranchIdEqualTo(branchId).andStatusIdIn(NormalStateMachine.noDelete());
        return courseMapper.selectByExample(example);
    }

    public List<Course> listByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        CourseExample example = new CourseExample();
        example.createCriteria().andIdIn(ids);
        return courseMapper.selectByExample(example);
    }

    @Override
    public Course getModel(Long id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Course model) {
        courseMapper.insert(model);
    }

    @Override
    public void delete(Long id) {
        courseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Course model) {
        courseMapper.updateByPrimaryKey(model);
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
