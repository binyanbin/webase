package com.bin.api.operate.base;


import com.bin.api.dao.mybatis.model.Session;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.operate.domain.cache.WebSessionDo;
import com.bin.api.operate.domain.db.SessionDo;
import com.bin.api.web.base.OperateDef;
import com.bin.webase.core.model.NoParam;
import com.bin.webase.core.model.OperateId;
import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class LogoutOp extends Operator<NoParam> {
    @Override
    protected Result dispose(NoParam param) {
        WebSessionDo webSession = getToken(WebSession.class).toDo();
        List<Session> sessions = SessionDo.REPOSITORY.listByUserId(webSession.getUserId(), webSession.getClientType());
        for (Session session : sessions) {
            remove(new SessionDo(session));
        }
        remove(webSession);
        if (!CollectionUtils.isEmpty(sessions)) {
            saveBranchLog(new SessionDo(sessions.get(0)), param, "");
        }
        return Result.success();
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.LOGOUT;
    }
}
