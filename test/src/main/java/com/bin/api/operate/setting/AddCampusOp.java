package com.bin.api.operate.setting;

import com.bin.api.controller.param.CampusParam;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.operate.domain.db.CampusDo;
import com.bin.api.web.base.OperateDef;
import com.bin.webase.core.operate.OperateId;
import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AddCampusOp extends Operator<CampusParam> {
    @Override
    protected Result dispose(CampusParam param) {
        WebSession webSession = getToken(WebSession.class);
        CampusDo campusDo = CampusDo.newInstance(param, webSession.getBranchId(), webSession.getEmployeeId(), getTime());
        updateState(campusDo);
        save(campusDo);
        saveBranchLog(campusDo);
        return Result.success(campusDo.getId());
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.ADD_CAMPUS;
    }
}
