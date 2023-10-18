package com.bin.api.operate.base;

import com.bin.api.controller.param.IdParam;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.operate.domain.cache.WebSessionDo;
import com.bin.api.operate.domain.db.BranchDo;
import com.bin.api.operate.domain.db.GuestBranchDo;
import com.bin.api.operate.domain.db.GuestDo;
import com.bin.api.dao.mybatis.model.Branch;
import com.bin.api.dao.mybatis.model.GuestBranch;
import com.bin.api.web.base.OperateDef;

import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import com.bin.webase.core.model.OperateId;
import com.bin.webase.exception.ErrorCheck;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class GuestSwitchBranchOp extends Operator<IdParam> {

    @Override
    protected Result dispose(IdParam param) {
        Branch branch = BranchDo.REPOSITORY.getModel(param.getId());
        ErrorCheck.checkNotNull(branch, "门店未找到");
        WebSessionDo webSession = getToken(WebSession.class).toDo();
        if (webSession.getBranchId().equals(branch.getId())) {
            return Result.success();
        }
        GuestDo guestDo = new GuestDo(webSession.getUserId());
        guestDo.setBranchId(branch.getId());
        save(guestDo);

        GuestBranch guestBranch = GuestBranchDo.REPOSITORY.getByGuestId(guestDo.getId(), branch.getId());
        ErrorCheck.checkNotNull(guestBranch, "门店未找到");

        webSession.switchBranch(branch.getId(), 0L);
        save(webSession);
        return Result.success(webSession.getModel());
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.GUEST_SWITCH_BRANCH;
    }
}
