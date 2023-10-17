package com.bin.api.operate.method;

import com.bin.api.operate.domain.db.CampusDo;
import com.bin.api.dao.mybatis.model.Campus;
import com.bin.webase.core.operate.Method;
import com.bin.webase.core.operate.OperateId;


import java.util.Date;
import java.util.List;

public class SetCampusStatus extends Method {
    private List<Campus> campuses;
    private Long employeeId;

    public SetCampusStatus(List<Long> ids, Long employeeId, OperateId operateId) {
        super(operateId);
        this.campuses = CampusDo.REPOSITORY.listByIds(ids);
        this.employeeId = employeeId;
    }

    @Override
    public void commit(Date now) {
        for (Campus campus : campuses) {
            CampusDo campusDo = new CampusDo(campus);
            campusDo.updateState(employeeId, now);
            updateState(campusDo);
            save(campusDo);
            saveBranchLog(operateId, campusDo);
        }
    }
}
