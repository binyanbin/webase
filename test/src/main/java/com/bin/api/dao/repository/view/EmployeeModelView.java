package com.bin.api.dao.repository.view;

import com.bin.api.operate.domain.db.EmployeeDo;
import com.bin.api.operate.domain.db.EmployeeFunctionDo;
import com.bin.api.dao.mybatis.model.Employee;
import com.bin.api.dao.mybatis.model.EmployeeFunction;
import com.bin.webase.core.model.FunctionId;
import com.bin.webase.core.query.ModelView;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

public class EmployeeModelView extends ModelView<Employee> {

    Set<Integer> functions;

    public EmployeeModelView(Long branchId, Long userId) {
        model = EmployeeDo.REPOSITORY.getEmployeeByUserId(branchId, userId);
    }

    public Set<Integer> listFunctions() {
        if (functions == null) {
            functions = Sets.newHashSet();
            if (model.getRoleId().equals(0L)) {
                functions = FunctionId.listId();
            } else {
                List<EmployeeFunction> employeeFunctions = EmployeeFunctionDo.REPOSITORY.listByEmployeeId(model.getId());
                for (EmployeeFunction employeeFunction : employeeFunctions) {
                    functions.add(employeeFunction.getFunctionId());
                }
            }
        }
        return functions;
    }
}
