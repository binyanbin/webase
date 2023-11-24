package com.bin.api.operate.domain.db;


import com.bin.api.operate.domain.cache.WebSessionDo;
import com.bin.api.dao.mybatis.model.Session;
import com.bin.api.dao.repository.SessionRepository;
import com.bin.webase.core.context.WebaseContext;
import com.bin.webase.core.context.IRepository;
import com.bin.webase.core.entity.DbDomain;

import java.util.Date;

public class SessionDo extends DbDomain<Session> {

    public static SessionRepository REPOSITORY = WebaseContext.getRepository(SessionRepository.class);

    public SessionDo(Session userSession) {
        super(userSession);
    }

    @Override
    public IRepository<Session> getRepository() {
        return REPOSITORY;
    }

    public static SessionDo newInstance(WebSessionDo webSession, Date now) {
        Session model = new Session();
        model.setBranchId(webSession.getBranchId());
        model.setEmployeeId(webSession.getEmployeeId());
        model.setUserId(webSession.getUserId());
        model.setClientType(webSession.getClientType());
        model.setSessionId(webSession.getUniqueId());
        model.setCreatedTime(now);
        model.setVersionId(webSession.getVersionId());
        model.setSecretKey(webSession.getSecretKey());
        model.setRevision(1);
        model.setUpdatedTime(now);
        return new SessionDo(model);
    }


    @Override
    public Long getId() {
        return model.getId();
    }

    @Override
    public void setId(Long id) {
        model.setId(id);
    }

}
