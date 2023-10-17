package com.bin.api.operate.setting;

import com.bin.api.controller.param.ClassTypeParam;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.operate.domain.db.ClassTypeDo;
import com.bin.api.web.base.OperateDef;
import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import com.bin.webase.core.operate.OperateId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UpdateClassTypeOp extends Operator<ClassTypeParam> {
    @Override
    protected Result dispose(ClassTypeParam param) {

        WebSession webSession = getToken(WebSession.class);
        Long id = param.getId();
        ClassTypeDo classType = new ClassTypeDo(id);
        updateState(classType);
        classType.update(param, webSession.getEmployeeId(), getTime());
        save(classType);
        saveBranchLog(getCommandId(), classType);
        return Result.success();
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.UPDATE_CLASS_TYPE;
    }
}
