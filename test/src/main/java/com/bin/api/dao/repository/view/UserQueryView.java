package com.bin.api.dao.repository.view;

import com.bin.api.operate.domain.db.BranchDo;
import com.bin.api.operate.domain.db.EmployeeDo;
import com.bin.api.dao.mybatis.model.Employee;
import com.bin.webase.core.model.IdName;

import com.bin.webase.core.query.QueryView;
import org.assertj.core.util.Lists;

import java.util.List;

public class UserQueryView extends QueryView<Long, Employee> {


    private List<IdName<Long>> branches;

    public UserQueryView(Long userId) {
        super(userId);
        root = EmployeeDo.REPOSITORY.listByUserId(userId);
    }


    public List<IdName<Long>> listBranch() {
        if (branches == null) {
            List<Long> branchIds = Lists.newArrayList();
            for (Employee employee : root) {
                branchIds.add(employee.getBranchId());
            }
            branches = BranchDo.REPOSITORY.listByIds(branchIds);
        }
        return branches;

    }

}
