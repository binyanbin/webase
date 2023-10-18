package com.bin.api.operate.guest;

import com.bin.api.controller.param.ScanBranchParam;
import com.bin.api.operate.domain.cache.WebSessionDo;
import com.bin.api.operate.domain.db.BranchDo;
import com.bin.api.operate.domain.db.GuestDo;
import com.bin.api.dao.enums.ClientType;
import com.bin.api.operate.method.AddGuestBranch;
import com.bin.api.operate.method.NewSession;
import com.bin.api.utils.UuidUtil;
import com.bin.api.web.base.OperateDef;
import com.bin.api.web.base.FunctionDef;
import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import com.bin.webase.core.model.OperateId;
import com.bin.webase.core.web.ThreadWebContextHolder;
import com.bin.webase.exception.ErrorCheck;
import org.assertj.core.util.Sets;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ScanBranchOp extends Operator<ScanBranchParam> {

    @Override
    protected Result dispose(ScanBranchParam param) {
        GuestDo guestDo = new GuestDo(param.getOpenId());
        if (guestDo.isNull()) {
            guestDo = GuestDo.newInstance(param.getOpenId(), "", param.getBranchId());
            save(guestDo);
        }

        Set<Integer> functionIds = Sets.newHashSet();
        functionIds.add(FunctionDef.GUEST_VISIT.getId());
        if (guestDo.hasPhone()) {
            functionIds.add(FunctionDef.MEMBER_FUNCTION_ID.getId());
        }

        BranchDo branch = new BranchDo(param.getBranchId());
        ErrorCheck.checkArgument(!branch.isNull(), "门店未找到");
        doMethod(new AddGuestBranch(guestDo.getId(), branch.getId(), getCommandId()));

        String versionId = ThreadWebContextHolder.getContext().getVersionId();
        String sessionId = UuidUtil.newUuidString();
        String secretKey = UuidUtil.newUuidString();
        WebSessionDo webSessionDo = WebSessionDo.newInstance(sessionId, secretKey, branch.getId(), guestDo.getId(), 0L, ClientType.guest.getId(), functionIds, getTime(), versionId);
        save(webSessionDo);
        doMethod(new NewSession(webSessionDo, getCommandId()));
        return Result.success(webSessionDo.getModel());
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.SCAN_BRANCH;
    }

}
