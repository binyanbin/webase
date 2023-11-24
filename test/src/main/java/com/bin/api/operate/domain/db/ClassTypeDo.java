package com.bin.api.operate.domain.db;

import com.bin.api.controller.param.ClassTypeParam;
import com.bin.api.dao.mybatis.model.ClassType;
import com.bin.api.dao.repository.ClassTypeRepository;
import com.bin.api.web.base.OperateDef;
import com.bin.webase.core.context.WebaseContext;
import com.bin.webase.core.context.IRepository;
import com.bin.webase.core.entity.DbDomain;
import com.bin.webase.core.entity.statemachine.IState;
import com.bin.webase.core.entity.statemachine.IStateMachine;
import com.bin.webase.core.entity.statemachine.NormalStateMachine;

import java.util.Date;

public class ClassTypeDo extends DbDomain<ClassType> implements IState {
    public static ClassTypeRepository REPOSITORY = WebaseContext.getRepository(ClassTypeRepository.class);
    public static final IStateMachine STATE_MACHINE = new NormalStateMachine(OperateDef.ADD_CLASS_TYPE, OperateDef.UPDATE_CLASS_TYPE,
            OperateDef.DELETE_CLASS_TYPE, OperateDef.DISABLE_CLASS_TYPE);

    public ClassTypeDo(Long id) {
        super(id);
    }

    public ClassTypeDo(ClassType model) {
        super(model);
    }

    @Override
    public IRepository<ClassType> getRepository() {
        return REPOSITORY;
    }

    public void update(ClassTypeParam param, Long employeeId, Date now) {
        model.setUpdatedBy(employeeId);
        model.setUpdatedTime(now);
        model.setName(param.getName());
        model.setDescription(param.getDescription());
    }

    public static ClassTypeDo newInstance(ClassTypeParam param, Long branchId, Long employeeId, Date now) {
        ClassType classType = new ClassType();
        classType.setBranchId(branchId);
        classType.setCreatedBy(employeeId);
        classType.setCreatedTime(now);
        classType.setUpdatedBy(employeeId);
        classType.setUpdatedTime(now);
        classType.setName(param.getName());
        classType.setDescription(param.getDescription());
        ClassTypeDo result = new ClassTypeDo(classType);
        return result;
    }

    public void modify(Long employeeId, Date now) {
        model.setUpdatedBy(employeeId);
        model.setUpdatedTime(now);
    }

    @Override
    public Long getId() {
        return model.getId();
    }

    @Override
    public void setId(Long id) {
        model.setId(id);
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
