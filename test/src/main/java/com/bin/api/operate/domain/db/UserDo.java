

package com.bin.api.operate.domain.db;

import com.bin.api.dao.enums.Status;
import com.bin.api.dao.mybatis.model.Session;
import com.bin.api.dao.mybatis.model.User;
import com.bin.api.dao.repository.UserRepository;
import com.bin.api.utils.Sha256;
import com.bin.webase.core.context.IRepository;
import com.bin.webase.core.context.WeContext;
import com.bin.webase.core.entity.DbDomain;
import com.bin.webase.core.entity.IBranch;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

public class UserDo extends DbDomain<User> implements IBranch {
    public static UserRepository REPOSITORY = WeContext.getRepository(UserRepository.class);

    public UserDo(Long id) {
        super(id);
    }

    public UserDo(String openId) {
        super(REPOSITORY.getByOpenId(openId));
    }


    public UserDo(User user) {
        super(user);
    }

    @Override
    public IRepository<User> getRepository() {
        return REPOSITORY;
    }

    public static UserDo newInstance(String phone, Date now, String openId, Long branchId) {
        User model = new User();
        model.setPhone(phone);
        model.setRevision(1);
        model.setUpdatedTime(now);
        model.setStatusId(Status.Valid.getValue());
        model.setOpenId(openId);
        model.setCreatedTime(now);
        model.setUpdatedTime(now);
        model.setBranchId(branchId);
        model.setPassword(Sha256.encrypt(phone.substring(phone.length() - 6) + "1234"));
        return new UserDo(model);
    }

    private List<SessionDo> sessions;

    public List<SessionDo> lisSession(Integer clientType) {
        if (sessions != null) {
            return sessions;
        } else {
            sessions = Lists.newArrayList();
            List<Session> sessionList = SessionDo.REPOSITORY.listByUserId(this.getId(), clientType);
            for (Session session : sessionList) {
                sessions.add(new SessionDo(session));
            }
            return sessions;
        }
    }


    public void login(Date now) {
        model.setLastLoginTime(now);
    }


    public void bind(String phone, Date now, Long branchId) {
        model.setPhone(phone);
        model.setUpdatedTime(now);
        model.setBranchId(branchId);
    }

    @Override
    public Long getId() {
        return model.getId();
    }

    @Override
    public void setId(Long id) {
        model.setId(id);
    }


    public String getPhone() {
        return model.getPhone();
    }


    public void setLastLoginTime(Date now) {
        model.setLastLoginTime(now);
        model.setUpdatedTime(now);
    }


    @Override
    public Long getBranchId() {
        return model.getBranchId();
    }

    @Override
    public boolean branchCache() {
        return false;
    }
}
