package com.bin.api.dao.repository;


import com.bin.api.dao.mybatis.base.EmployeeFunctionMapper;
import com.bin.api.dao.mybatis.model.EmployeeFunction;
import com.bin.api.dao.mybatis.model.EmployeeFunctionExample;
import com.bin.api.dao.repository.mapper.CommonSqlService;
import com.bin.webase.core.context.DoRepository;
import com.bin.webase.core.context.IRepository;

import java.util.List;

@DoRepository
public class EmployeeFunctionRepository implements IRepository<EmployeeFunction> {
    private static final String TABLE_NAME = "pl_employee_function";
    private final EmployeeFunctionMapper employeeFunctionMapper;
    private final CommonSqlService commonMapper;

    public EmployeeFunctionRepository(EmployeeFunctionMapper employeeFunctionMapper, CommonSqlService commonMapper) {
        this.employeeFunctionMapper = employeeFunctionMapper;
        this.commonMapper = commonMapper;
    }

    public List<EmployeeFunction> listByEmployeeId(Long employeeId) {
        EmployeeFunctionExample example = new EmployeeFunctionExample();
        example.createCriteria().andEmployeeIdEqualTo(employeeId);
        return employeeFunctionMapper.selectByExample(example);
    }


    public void deleteByEmployeeId(Long employeeId) {
        EmployeeFunctionExample example = new EmployeeFunctionExample();
        example.createCriteria().andEmployeeIdEqualTo(employeeId);
        employeeFunctionMapper.deleteByExample(example);
    }


    @Override
    public EmployeeFunction getModel(Long id) {
        return employeeFunctionMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(EmployeeFunction model) {
        employeeFunctionMapper.insert(model);
    }

    @Override
    public void delete(Long id) {
        employeeFunctionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(EmployeeFunction model) {
        employeeFunctionMapper.updateByPrimaryKey(model);
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
