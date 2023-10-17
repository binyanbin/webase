package com.bin.api.operate.domain.db;

import com.bin.api.controller.param.CourseParam;
import com.bin.api.dao.mybatis.model.Course;
import com.bin.api.dao.repository.CourseRepository;
import com.bin.api.web.base.OperateDef;
import com.bin.webase.core.context.WeContext;
import com.bin.webase.core.context.IRepository;
import com.bin.webase.core.entity.DbDomain;
import com.bin.webase.core.entity.statemachine.IState;
import com.bin.webase.core.entity.statemachine.IStateMachine;
import com.bin.webase.core.entity.statemachine.NormalStateMachine;

import java.util.Date;

public class CourseDo extends DbDomain<Course> implements IState {
    public static final CourseRepository REPOSITORY = WeContext.getRepository(CourseRepository.class);
    public static final IStateMachine STATE_MACHINE = new NormalStateMachine(OperateDef.ADD_COURSE, OperateDef.UPDATE_COURSE,
            OperateDef.DELETE_COURSE, OperateDef.DISABLE_COURSE);

    public CourseDo(Long id) {
        super(id);
    }

    public CourseDo(Course model) {
        super(model);
    }

    @Override
    public IRepository<Course> getRepository() {
        return REPOSITORY;
    }

    @Override
    public Long getId() {
        return model.getId();
    }

    @Override
    public void setId(Long id) {
        model.setId(id);
    }

    public static CourseDo newInstance(CourseParam param, Long branchId, Long employeeId, Date now) {
        Course course = new Course();
        course.setBranchId(branchId);
        course.setCreatedBy(employeeId);
        course.setCreatedTime(now);
        course.setUpdatedBy(employeeId);
        course.setUpdatedTime(now);
        course.setIntroduce(param.getIntroduce());
        course.setName(param.getName());
        course.setNeedToKnow(param.getNeedToKnow());
        course.setPeriod(param.getPeriod());
        course.setClassTypeId(param.getClassTypeId());
        CourseDo result = new CourseDo(course);
        return result;
    }

    public void update(CourseParam param, Long employeeId, Date now) {
        model.setUpdatedBy(employeeId);
        model.setUpdatedTime(now);
        model.setIntroduce(param.getIntroduce());
        model.setName(param.getName());
        model.setNeedToKnow(param.getNeedToKnow());
        model.setPeriod(param.getPeriod());
        model.setClassTypeId(param.getClassTypeId());
    }

    public void updateState(Long employeeId, Date now) {
        model.setUpdatedBy(employeeId);
        model.setUpdatedTime(now);
    }

    @Override
    public Integer getStateId() {
        return model.getStatusId();
    }

    @Override
    public void setStateId(Integer stateId) {
        model.setStatusId(stateId);
    }

    @Override
    public IStateMachine getStateMachine() {
        return STATE_MACHINE;
    }

}
