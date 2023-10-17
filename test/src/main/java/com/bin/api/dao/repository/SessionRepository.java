package com.bin.api.dao.repository;


import com.bin.api.dao.mybatis.base.SessionMapper;
import com.bin.api.dao.mybatis.model.Session;
import com.bin.api.dao.mybatis.model.SessionExample;
import com.bin.api.dao.repository.mapper.CommonSqlService;
import com.bin.webase.core.context.DoRepository;
import com.bin.webase.core.context.IRepository;
import com.bin.webase.exception.ErrorCheck;
import org.springframework.util.CollectionUtils;

import java.util.List;

@DoRepository
public class SessionRepository implements IRepository<Session> {
    private static final String TABLE_NAME = "pl_session";
    private final SessionMapper sessionMapper;
    private final CommonSqlService commonMapper;

    public SessionRepository(SessionMapper sessionMapper, CommonSqlService commonMapper) {
        this.sessionMapper = sessionMapper;
        this.commonMapper = commonMapper;
    }

    public List<Session> listByUserId(Long userId, Integer clientType) {
        SessionExample example = new SessionExample();
        SessionExample.Criteria criteria = example.createCriteria().andUserIdEqualTo(userId);
        if (clientType != null) {
            criteria.andClientTypeEqualTo(clientType);
        }
        return sessionMapper.selectByExample(example);
    }

    public List<Session> listByEmployeeId(Long employeeId) {
        SessionExample example = new SessionExample();
        example.createCriteria().andEmployeeIdEqualTo(employeeId);
        return sessionMapper.selectByExample(example);
    }

    public Session getBySessionId(String sessionId) {
        SessionExample example = new SessionExample();
        example.createCriteria().andSessionIdEqualTo(sessionId);
        List<Session> sessions = sessionMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(sessions)) {
            return null;
        } else {
            return sessions.get(0);
        }
    }

    @Override
    public Session getModel(Long id) {
        return sessionMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Session model) {
        sessionMapper.insert(model);
    }

    @Override
    public void delete(Long id) {
        sessionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Session model) {
        SessionExample example = new SessionExample();
        example.createCriteria().andIdEqualTo(model.getId()).andRevisionEqualTo(model.getRevision());
        model.setRevision(model.getRevision() + 1);
        int result = sessionMapper.updateByExample(model, example);
        ErrorCheck.checkServerBusy(result > 0);
    }

    @Override
    public Long getMaxId() {
        return commonMapper.getMaxId(TABLE_NAME);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

}
