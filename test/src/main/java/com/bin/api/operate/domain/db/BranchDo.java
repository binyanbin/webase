package com.bin.api.operate.domain.db;

import com.bin.api.controller.param.BranchParam;
import com.bin.api.controller.param.PositionParam;
import com.bin.api.dao.enums.Status;
import com.bin.api.dao.mybatis.model.Branch;
import com.bin.api.dao.repository.BranchRepository;

import com.bin.webase.core.context.WeContext;
import com.bin.webase.core.context.IRepository;
import com.bin.webase.core.entity.DbDomain;

import java.util.Date;

public class BranchDo extends DbDomain<Branch> {

    public static final BranchRepository REPOSITORY = WeContext.getRepository(BranchRepository.class);

    public BranchDo(Long branchId) {
        super(branchId);
    }

    public BranchDo(Branch branch) {
        super(branch);
    }

    @Override
    public IRepository<Branch> getRepository() {
        return REPOSITORY;
    }

    public static BranchDo newInstance(BranchParam param, Date now) {
        Branch branch = new Branch();
        branch.setName(param.getName());
        branch.setIntroduce(param.getIntroduce());
        branch.setAddress(param.getAddress());
        branch.setPhone(param.getPhone());
        branch.setCreatedTime(now);
        branch.setCreatedBy(0L);
        branch.setStatusId(Status.Invalid.getValue());
        branch.setUpdatedBy(0L);
        branch.setUpdatedTime(now);
        return new BranchDo(branch);
    }

    public void setPosition(PositionParam param) {
        model.setLongitudeandlatitude(param.getLongitude() + "," + param.getLatitude());
    }

    public void active(Date date) {
        model.setActiveTime(date);
        model.setStatusId(Status.Valid.getValue());
    }

    public void update(BranchParam param, Long employeeId, Date now) {
        model.setName(param.getName());
        model.setIntroduce(param.getIntroduce());
        model.setAddress(param.getAddress());
        model.setPhone(param.getPhone());
        model.setUpdatedBy(employeeId);
        model.setUpdatedTime(now);
    }

    public String getName() {
        return model.getName();
    }

    @Override
    public Long getId() {
        return model.getId();
    }

    @Override
    public void setId(Long id) {
        model.setId(id);
    }
}
