package com.bin.api.operate.domain.db;

import com.bin.api.controller.param.CampusParam;
import com.bin.api.dao.mybatis.model.Campus;
import com.bin.api.dao.repository.CampusRepository;
import com.bin.api.web.base.OperateDef;
import com.bin.webase.core.context.IRepository;
import com.bin.webase.core.context.WebaseContext;
import com.bin.webase.core.entity.DbDomain;
import com.bin.webase.core.entity.IDbCache;
import com.bin.webase.core.entity.statemachine.IState;
import com.bin.webase.core.entity.statemachine.IStateMachine;
import com.bin.webase.core.entity.statemachine.NormalStateMachine;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CampusDo extends DbDomain<Campus> implements IState, IDbCache {
    public static CampusRepository REPOSITORY = WebaseContext.getRepository(CampusRepository.class);
    public static final IStateMachine STATE_MACHINE = new NormalStateMachine(OperateDef.ADD_CAMPUS, OperateDef.UPDATE_CAMPUS,
            OperateDef.DELETE_CAMPUS, OperateDef.DISABLE_CAMPUS);

    public CampusDo(Long id) {
        super(id);
    }

    public CampusDo(Campus model) {
        super(model);
    }

    @Override
    public IRepository<Campus> getRepository() {
        return REPOSITORY;
    }

    public void update(CampusParam param, Long employeeId, Date now) {
        model.setUpdatedBy(employeeId);
        model.setUpdatedTime(now);
        model.setName(param.getName());
        model.setAddress(param.getAddress());
    }

    public void updateState(Long employeeId, Date now) {
        model.setUpdatedBy(employeeId);
        model.setUpdatedTime(now);
    }

    public static CampusDo newInstance(CampusParam param, Long branchId, Long employeeId, Date now) {
        Campus campus = new Campus();
        campus.setBranchId(branchId);
        campus.setCreatedBy(employeeId);
        campus.setCreatedTime(now);
        campus.setUpdatedBy(employeeId);
        campus.setUpdatedTime(now);
        campus.setName(param.getName());
        campus.setAddress(param.getAddress());
        return new CampusDo(campus);
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

    @Override
    public int getCacheSecond() {
        return (int) TimeUnit.DAYS.toSeconds(1);
    }
}
