package com.bin.api.operate.setting;

import com.bin.api.controller.param.IdListParam;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.operate.method.SetCourseStatus;
import com.bin.api.web.base.OperateDef;
import com.bin.api.web.base.FunctionDef;
import com.bin.webase.core.model.FunctionId;
import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import com.bin.webase.core.model.OperateId;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class EnableCourseOp extends Operator<IdListParam> {
    @Override
    protected Result dispose(IdListParam param) {
        WebSession webSession = getToken(WebSession.class);
        doMethod(new SetCourseStatus(param.getIds(), webSession.getEmployeeId(), getCommandId()));
        return Result.success();
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.ENABLE_COURSE;
    }

    @Override
    public List<FunctionId> getFunction() {
        return Lists.newArrayList(FunctionDef.COURSE_MANAGE);
    }
}
