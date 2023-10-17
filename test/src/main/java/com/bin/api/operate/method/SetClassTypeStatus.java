package com.bin.api.operate.method;

import com.bin.api.operate.domain.db.ClassTypeDo;
import com.bin.api.dao.mybatis.model.ClassType;
import com.bin.webase.core.operate.Method;
import com.bin.webase.core.operate.OperateId;


import java.util.Date;
import java.util.List;

public class SetClassTypeStatus extends Method {
    private List<ClassType> classTypes;
    private Long employeeId;

    public SetClassTypeStatus(List<Long> ids, Long employeeId, OperateId operateId) {
        super(operateId);
        this.classTypes = ClassTypeDo.REPOSITORY.listByIds(ids);
        this.employeeId = employeeId;
    }

    @Override
    public void commit(Date now) {
        for (ClassType classType : classTypes) {
            ClassTypeDo classTypeDo = new ClassTypeDo(classType);
            classTypeDo.modify(employeeId, now);
            updateState(classTypeDo);
            save(classTypeDo);
            saveBranchLog(operateId, classTypeDo);
        }
    }
}
