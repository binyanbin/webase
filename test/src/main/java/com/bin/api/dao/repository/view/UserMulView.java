package com.bin.api.dao.repository.view;

import com.bin.api.operate.domain.db.BranchDo;
import com.bin.api.operate.domain.db.EmployeeDo;
import com.bin.api.dao.mybatis.model.Employee;
import com.bin.webase.core.model.IdName;

import com.bin.webase.core.query.MulView;
import org.assertj.core.util.Lists;

import java.util.List;

public class UserMulView extends MulView<Long, Employee> {


    private List<IdName<Long>> branches;

    public UserMulView(Long userId) {
        super(userId);
        list = EmployeeDo.REPOSITORY.listByUserId(userId);
    }


    public List<IdName<Long>> listBranch() {
        if (branches == null) {
            List<Long> branchIds = Lists.newArrayList();
            for (Employee employee : list) {
                branchIds.add(employee.getBranchId());
            }
            branches = BranchDo.REPOSITORY.listByIds(branchIds);
        }
        return branches;

    }

}
