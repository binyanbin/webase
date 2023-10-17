package com.bin.api.dao.repository.view;

import com.bin.api.operate.domain.db.EmployeeDo;
import com.bin.api.operate.domain.db.EmployeeFunctionDo;
import com.bin.api.dao.mybatis.model.Employee;
import com.bin.api.dao.mybatis.model.EmployeeFunction;
import com.bin.webase.core.entity.FunctionId;
import com.bin.webase.core.query.View;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

public class EmployeeView extends View<Employee> {

    Set<Integer> functions;

    public EmployeeView(Long branchId, Long userId) {
        root = EmployeeDo.REPOSITORY.getEmployeeByUserId(branchId, userId);
    }

    public Set<Integer> listFunctions() {
        if (functions == null) {
            functions = Sets.newHashSet();
            if (root.getRoleId().equals(0L)) {
                functions = FunctionId.listId();
            } else {
                List<EmployeeFunction> employeeFunctions = EmployeeFunctionDo.REPOSITORY.listByEmployeeId(root.getId());
                for (EmployeeFunction employeeFunction : employeeFunctions) {
                    functions.add(employeeFunction.getFunctionId());
                }
            }
        }
        return functions;
    }
}
