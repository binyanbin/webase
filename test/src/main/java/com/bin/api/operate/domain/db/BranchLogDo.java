package com.bin.api.operate.domain.db;


import com.alibaba.fastjson.JSON;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.dao.mybatis.model.BranchLog;
import com.bin.api.dao.repository.BranchLogRepository;

import com.bin.webase.core.context.WeContext;
import com.bin.webase.core.context.IRepository;
import com.bin.webase.core.entity.DbDomain;
import com.bin.webase.core.operate.IParam;
import com.bin.webase.core.operate.OperateId;
import com.bin.webase.core.web.ApiToken;
import com.bin.webase.core.web.ThreadWebContextHolder;
import com.bin.webase.core.web.WebContext;

public class BranchLogDo extends DbDomain<BranchLog> {
    public static BranchLogRepository REPOSITORY = WeContext.getRepository(BranchLogRepository.class);


    public BranchLogDo(Long id) {
        super(id);
    }

    public BranchLogDo(BranchLog branchLog) {
        super(branchLog);
    }

    @Override
    public IRepository<BranchLog> getRepository() {
        return REPOSITORY;
    }

    public static DbDomain newInstance(OperateId command, DbDomain domain, IParam param, String msg) {

        WebContext webContext = ThreadWebContextHolder.getContext();
        if (webContext != null) {
            ApiToken token = webContext.getToken();
            if (token != null) {
                BranchLog branchLog = new BranchLog();
                WebSession webSession = (WebSession) token;
                branchLog.setCreatedBy(webSession.getEmployeeId());
                branchLog.setCreatedTime(ThreadWebContextHolder.getContext().getBeginTime());
                branchLog.setBranchId(webSession.getBranchId());
                branchLog.setClientType(webSession.getClientType());
                branchLog.setEventId(command.getId());
                branchLog.setEventName(command.getName());
                branchLog.setRemark(msg);
                branchLog.setInfo(JSON.toJSONString(param));
                branchLog.setModelVal(JSON.toJSONString(domain.getModel()));
                return new BranchLogDo(branchLog);
            }
        }
        return null;
    }


    public Long getId() {
        return model.getId();
    }

    public void setId(Long id) {
        model.setId(id);
    }
}