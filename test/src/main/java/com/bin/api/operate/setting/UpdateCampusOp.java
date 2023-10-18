package com.bin.api.operate.setting;

import com.bin.api.controller.param.CampusParam;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.operate.domain.db.CampusDo;
import com.bin.api.web.base.OperateDef;
import com.bin.webase.core.model.OperateId;
import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UpdateCampusOp extends Operator<CampusParam> {
    @Override
    protected Result dispose(CampusParam param) {
        WebSession webSession = getToken(WebSession.class);
        Long id = param.getId();
        CampusDo campusDo = new CampusDo(id);
        campusDo.update(param, webSession.getEmployeeId(), getTime());
        updateState(campusDo);
        save(campusDo);
        saveBranchLog(campusDo, param, "");
        return Result.success();
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.UPDATE_CAMPUS;
    }
}
