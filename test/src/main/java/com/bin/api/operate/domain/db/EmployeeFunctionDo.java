package com.bin.api.operate.domain.db;


import com.bin.api.dao.mybatis.model.EmployeeFunction;
import com.bin.api.dao.repository.EmployeeFunctionRepository;
import com.bin.webase.core.context.WebaseContext;
import com.bin.webase.core.context.IRepository;
import com.bin.webase.core.entity.DbDomain;
import com.bin.webase.core.entity.IBranch;

public class EmployeeFunctionDo extends DbDomain<EmployeeFunction> implements IBranch {

    public static EmployeeFunctionRepository REPOSITORY = WebaseContext.getRepository(EmployeeFunctionRepository.class);

    public EmployeeFunctionDo(EmployeeFunction employeeFunction) {
        super(employeeFunction);
    }

    @Override
    public IRepository<EmployeeFunction> getRepository() {
        return REPOSITORY;
    }

    public static EmployeeFunctionDo newInstance(Long userId, Long branchId, Long employeeId, Integer functionId) {
        EmployeeFunction model = new EmployeeFunction();
        model.setBranchId(branchId);
        model.setEmployeeId(employeeId);
        model.setFunctionId(functionId);
        model.setUserId(userId);
        return new EmployeeFunctionDo(model);
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
    public Long getBranchId() {
        return model.getBranchId();
    }

    @Override
    public boolean branchCache() {
        return false;
    }
}
