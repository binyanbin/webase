package com.bin.api.operate.domain.db;


import com.bin.api.dao.enums.Status;
import com.bin.api.dao.mybatis.model.Employee;
import com.bin.api.dao.repository.EmployeeRepository;
import com.bin.webase.core.context.WeContext;
import com.bin.webase.core.context.IRepository;
import com.bin.webase.core.entity.DbDomain;
import com.bin.webase.core.entity.IBranch;

import java.util.Date;

public class EmployeeDo extends DbDomain<Employee> implements IBranch {

    public static EmployeeRepository REPOSITORY = WeContext.getRepository(EmployeeRepository.class);

    public EmployeeDo(Long employeeId) {
        super(employeeId);
    }

    public EmployeeDo(Employee employee) {
       super(employee);
    }

    @Override
    public IRepository<Employee> getRepository() {
        return REPOSITORY;
    }


    public static EmployeeDo newInstance(Long branchId, Long userId, String name, Long creatorId, Long roleId, Date now) {
        Employee model = new Employee();
        model.setBranchId(branchId);
        model.setName(name);
        model.setCreatedBy(creatorId);
        model.setUpdatedBy(creatorId);
        model.setRoleId(roleId);
        model.setStatusId(Status.Valid.getValue());
        model.setUserId(userId);
        model.setRevision(0);
        model.setCreatedTime(now);
        model.setUpdatedTime(now);
        return new EmployeeDo(model);
    }

    public Long getBranchId() {
        return model.getBranchId();
    }

    public Long getUserId() {
        return model.getUserId();
    }

    public Long getRoleId() {
        return model.getRoleId();
    }

    public Integer getStatusId() {
        return model.getStatusId();
    }

    public void active(Long userId, Date now) {
        model.setUserId(userId);
        model.setUpdatedTime(now);
    }

    public void delete(Date now, Long operatorId) {
        model.setStatusId(Status.Delete.getValue());
        model.setUpdatedBy(operatorId);
        model.setUpdatedTime(now);
    }

    public void disable(Date now, Long operatorId) {
        model.setStatusId(Status.Invalid.getValue());
        model.setUpdatedBy(operatorId);
        model.setUpdatedTime(now);
    }

    public void enable(Date now, Long operatorId) {
        model.setStatusId(Status.Valid.getValue());
        model.setUpdatedBy(operatorId);
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

}
