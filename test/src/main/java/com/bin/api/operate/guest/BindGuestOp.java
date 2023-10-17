package com.bin.api.operate.guest;

import com.bin.api.controller.param.BindGuestParam;
import com.bin.api.operate.domain.db.GuestDo;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.web.base.OperateDef;
import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import com.bin.webase.core.operate.OperateId;

import org.springframework.stereotype.Service;

@Service
public class BindGuestOp extends Operator<BindGuestParam> {
    @Override
    protected Result dispose(BindGuestParam param) {
        WebSession webSession = getToken(WebSession.class);
        GuestDo guestDo = new GuestDo(webSession.getUserId());
        guestDo.bind(param.getName(),param.getPhone());
        save(guestDo);
        return Result.success();
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.BIND_GUEST;
    }

}
