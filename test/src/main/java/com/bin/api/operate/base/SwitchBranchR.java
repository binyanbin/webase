package com.bin.api.operate.base;


import com.bin.api.controller.param.IdParam;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.operate.domain.cache.WebSessionDo;
import com.bin.api.operate.domain.db.BranchDo;
import com.bin.api.dao.enums.ClientType;
import com.bin.api.dao.mybatis.model.Branch;
import com.bin.api.dao.repository.view.EmployeeView;

import com.bin.api.operate.method.AddGuestBranch;
import com.bin.api.operate.method.NewSession;
import com.bin.api.web.base.OperateDef;

import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import com.bin.webase.core.operate.OperateId;
import com.bin.webase.exception.ErrorCheck;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
public class SwitchBranchR extends Operator<IdParam> {
    @Override
    protected Result dispose(IdParam param) {
        Branch branch = BranchDo.REPOSITORY.getModel(param.getId());
        ErrorCheck.checkNotNull(branch, "门店未找到");
        WebSessionDo webSession = getToken(WebSession.class).toDo();
        if (webSession.getClientType().equals(ClientType.manager.getId())) {
            EmployeeView employeeView = new EmployeeView(branch.getId(), webSession.getUserId());
            Set<Integer> functions = employeeView.listFunctions();
            webSession.switchBranch(branch.getId(), employeeView.getRoot().getId(), functions);
            save(webSession);
        } else {
            webSession.switchBranch(branch.getId(), 0L);
            doMethod(new AddGuestBranch(webSession.getUserId(), branch.getId(), getCommandId()));
        }
        doMethod(new NewSession(webSession,getCommandId()));
        return Result.success(webSession.toRestfulToken());
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.SWITCH_BRANCH;
    }
}
