package com.bin.api.operate.setting;

import com.bin.api.controller.param.ClassTypeParam;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.operate.domain.db.ClassTypeDo;
import com.bin.api.web.base.OperateDef;
import com.bin.webase.core.operate.OperateId;
import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AddClassTypeOp extends Operator<ClassTypeParam> {
    @Override
    protected Result dispose(ClassTypeParam param) {
        WebSession webSession = getToken(WebSession.class);
        ClassTypeDo classTypeDo = ClassTypeDo.newInstance(param, webSession.getBranchId(), webSession.getEmployeeId(), getTime());
        updateState(classTypeDo);
        save(classTypeDo);
        saveBranchLog(classTypeDo);
        return Result.success(classTypeDo.getId());
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.ADD_CLASS_TYPE;
    }
}
