package com.bin.api.operate.domain.cache;

import com.alibaba.fastjson.JSON;
import com.bin.api.dao.mybatis.model.GuestBranch;
import com.bin.api.dao.mybatis.model.Session;
import com.bin.api.operate.domain.db.GuestBranchDo;
import com.bin.api.operate.domain.db.SessionDo;
import com.bin.webase.core.entity.CacheDomain;
import com.bin.webase.core.web.ApiToken;
import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.util.Lists;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class WebSessionDo extends CacheDomain<WebSession> {

    public WebSessionDo(String id) {
        super(id, WebSession.class);
    }

    public WebSessionDo() {
    }

    public WebSessionDo(WebSession webSession) {
        super(webSession);
    }

    public WebSessionDo(String sessionId, String secretKey, Long branchId, Long userId, Long employeeId, Integer clientType, Set<Integer> functionIds, Date now, String versionId) {
        Date expiredTime = DateUtils.addSeconds(now, (int) getSaveTime());
        WebSession webSession = new WebSession(sessionId, secretKey, branchId, userId, employeeId, clientType, functionIds, expiredTime, versionId);
        model = webSession;
    }

    @Override
    public long getSaveTime() {
        return TimeUnit.DAYS.toSeconds(60);
    }

    public static WebSessionDo parse(String json) {
        WebSession webSession = JSON.parseObject(json, WebSession.class);
        return new WebSessionDo(webSession);
    }

    public static WebSessionDo newInstance(String sessionId, String secretKey, Long branchId, Long userId, Long employeeId, Integer clientType, Set<Integer> functionIds, Date now, String versionId) {
        return new WebSessionDo(sessionId, secretKey, branchId, userId, employeeId, clientType, functionIds, now, versionId);
    }


    public String getJsonString() {
        return this.toJson();
    }

    public Integer getClientType() {
        return model.getClientType();
    }

    public String getSecretKey() {
        return model.getSecretKey();
    }

    public Long getUserId() {
        return model.getUserId();
    }

    public Long getEmployeeId() {
        return model.getEmployeeId();
    }

    public Long getBranchId() {
        return model.getBranchId();
    }

    public String getVersionId() {
        return model.getVersionId();
    }

    public void switchBranch(Long branchId, Long employeeId, Set<Integer> functions) {
        model.setBranchId(branchId);
        model.setEmployeeId(employeeId);
        model.setFunctionIds(functions);
    }

    public void switchBranch(Long branchId, Long employeeId) {
        model.setBranchId(branchId);
        model.setEmployeeId(employeeId);
    }

    public WebSession getModel() {
        return model;
    }

    public ApiToken toRestfulToken() {
        return new ApiToken(getUniqueId(), getSecretKey(), model.getFunctionIds(), model.getExpirationTime());
    }

    public List<SessionDo> listSession() {
        List<Session> sessions = SessionDo.REPOSITORY.listByUserId(this.getUserId(), this.getClientType());
        List<SessionDo> result = Lists.newArrayList();
        for (Session session : sessions) {
            result.add(new SessionDo(session));
        }
        return result;
    }

    public List<GuestBranchDo> listGuestBranch() {
        List<GuestBranch> guestBranches = GuestBranchDo.REPOSITORY.listByGuestId(this.getUserId());
        List<GuestBranchDo> result = Lists.newArrayList();
        for (GuestBranch guestBranch : guestBranches) {
            result.add(new GuestBranchDo(guestBranch));
        }
        return result;
    }
}
