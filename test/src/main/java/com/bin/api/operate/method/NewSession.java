package com.bin.api.operate.method;

import com.bin.api.dao.mybatis.model.Session;
import com.bin.api.operate.domain.cache.WebSessionDo;
import com.bin.api.operate.domain.db.SessionDo;
import com.bin.webase.core.operate.Method;
import com.bin.webase.core.model.OperateId;

import java.util.Date;
import java.util.List;

public class NewSession extends Method {

    private WebSessionDo webSession;

    public NewSession(WebSessionDo webSession, OperateId operateId) {
        super(operateId);
        this.webSession = webSession;
    }

    @Override
    public void commit(Date now) {
        List<Session> sessions = SessionDo.REPOSITORY.listByUserId(webSession.getUserId(), webSession.getClientType());
        for (Session session : sessions) {
            remove(new SessionDo(session));
        }
        save(SessionDo.newInstance(webSession, now));
    }
}
