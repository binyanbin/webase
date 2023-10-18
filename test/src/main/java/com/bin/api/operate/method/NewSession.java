package com.bin.api.operate.method;

import com.bin.api.operate.domain.cache.WebSessionDo;
import com.bin.api.operate.domain.db.SessionDo;
import com.bin.webase.core.model.OperateId;
import com.bin.webase.core.operate.Method;

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
        List<SessionDo> sessions = webSession.listSession();
        for (SessionDo session : sessions) {
            remove(session);
        }
        save(SessionDo.newInstance(webSession, now));
    }
}
