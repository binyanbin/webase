package com.bin.api.dao.repository;


import com.bin.api.dao.enums.Status;
import com.bin.api.dao.mybatis.base.EmployeeMapper;
import com.bin.api.dao.mybatis.model.Employee;
import com.bin.api.dao.mybatis.model.EmployeeExample;
import com.bin.api.dao.repository.mapper.CommonSqlService;
import com.bin.webase.core.context.DoRepository;
import com.bin.webase.core.context.IRepository;
import com.bin.webase.exception.ErrorCheck;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;

@DoRepository
public class EmployeeRepository implements IRepository<Employee> {
    private static final String TABLE_NAME = "pl_employee";
    private final EmployeeMapper employeeMapper;
    private final CommonSqlService commonMapper;

    public EmployeeRepository(EmployeeMapper employeeMapper, CommonSqlService commonMapper) {
        this.employeeMapper = employeeMapper;
        this.commonMapper = commonMapper;
    }

    public List<Employee> listByRoleId(Long branchId, Long roleId) {
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andBranchIdEqualTo(branchId).andRoleIdEqualTo(roleId).andStatusIdEqualTo(Status.Valid.getValue());
        return employeeMapper.selectByExample(employeeExample);
    }

    public boolean existsEmployee(Long branchId, Long roleId) {
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andBranchIdEqualTo(branchId).andRoleIdEqualTo(roleId).andStatusIdEqualTo(Status.Valid.getValue());
        return employeeMapper.countByExample(employeeExample) > 0;
    }

    public List<Employee> listByUserId(Long userId) {
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andUserIdEqualTo(userId).andStatusIdEqualTo(Status.Valid.getValue());
        return employeeMapper.selectByExample(employeeExample);
    }

    public Employee getEmployeeByUserId(Long branchId, Long userId) {
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andBranchIdEqualTo(branchId).andUserIdEqualTo(userId).andStatusIdEqualTo(Status.Valid.getValue());
        List<Employee> employees = employeeMapper.selectByExample(employeeExample);
        if (CollectionUtils.isEmpty(employees)) {
            return null;
        } else {
            return employees.get(0);
        }
    }


    public List<Employee> listByBranchId(Long branchId, List<Integer> statusIds) {
        EmployeeExample example = new EmployeeExample();
        example.createCriteria().andBranchIdEqualTo(branchId).andStatusIdIn(statusIds);
        return employeeMapper.selectByExample(example);
    }


    public List<Employee> listByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Lists.newArrayList();
        } else {
            EmployeeExample example = new EmployeeExample();
            example.createCriteria().andIdIn(ids);
            return employeeMapper.selectByExample(example);
        }
    }

    @Override
    public Employee getModel(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Employee model) {
        employeeMapper.insert(model);
    }

    @Override
    public void delete(Long id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Employee model) {
        EmployeeExample example = new EmployeeExample();
        example.createCriteria().andIdEqualTo(model.getId()).andRevisionEqualTo(model.getRevision());
        model.setRevision(model.getRevision() + 1);
        int result = employeeMapper.updateByExample(model, example);
        ErrorCheck.checkServerBusy(result > 0);
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
