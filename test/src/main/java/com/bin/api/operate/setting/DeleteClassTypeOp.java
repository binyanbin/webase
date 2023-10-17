package com.bin.api.operate.setting;

import com.bin.api.controller.param.IdListParam;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.operate.method.SetClassTypeStatus;
import com.bin.api.web.base.OperateDef;
import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import com.bin.webase.core.operate.OperateId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class DeleteClassTypeOp extends Operator<IdListParam> {
    @Override
    protected Result dispose(IdListParam param) {
        WebSession webSession = getToken(WebSession.class);
        doMethod(new SetClassTypeStatus(param.getIds(), webSession.getEmployeeId(), getCommandId()));
        return Result.success();
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.DELETE_CLASS_TYPE;
    }
}
