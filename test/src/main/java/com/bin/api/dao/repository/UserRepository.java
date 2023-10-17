package com.bin.api.dao.repository;

import com.bin.api.dao.enums.Status;
import com.bin.api.dao.mybatis.base.UserMapper;
import com.bin.api.dao.mybatis.model.User;
import com.bin.api.dao.mybatis.model.UserExample;
import com.bin.api.dao.repository.mapper.CommonSqlService;
import com.bin.webase.core.context.DoRepository;
import com.bin.webase.core.context.IRepository;
import com.bin.webase.exception.ErrorCheck;
import org.springframework.util.CollectionUtils;

import java.util.List;

@DoRepository
public class UserRepository implements IRepository<User> {
    private static final String TABLE_NAME = "pl_user";
    private final UserMapper mapper;
    private final CommonSqlService commonMapper;

    public UserRepository(UserMapper mapper, CommonSqlService commonMapper) {
        this.mapper = mapper;
        this.commonMapper = commonMapper;
    }

    public List<User> listUserByStatusId(Integer statusId) {
        UserExample example = new UserExample();
        example.createCriteria().andStatusIdEqualTo(statusId);
        return mapper.selectByExample(example);
    }

    public boolean existsUser(String phone) {
        UserExample example = new UserExample();
        example.createCriteria().andPhoneEqualTo(phone).andStatusIdEqualTo(Status.Valid.getValue());
        return mapper.countByExample(example) > 0L;
    }

    public User getByPhone(String phone) {
        UserExample example = new UserExample();
        example.createCriteria().andPhoneEqualTo(phone);
        List<User> users = mapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        } else {
            return users.get(0);
        }
    }

    public User getByOpenId(String openId) {
        UserExample example = new UserExample();
        example.createCriteria().andOpenIdEqualTo(openId);
        List<User> users = mapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        } else {
            return users.get(0);
        }
    }


    @Override
    public User getModel(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(User model) {
        mapper.insert(model);
    }

    @Override
    public void delete(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(User model) {
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(model.getId()).andRevisionEqualTo(model.getRevision());
        model.setRevision(model.getRevision() + 1);
        int result = mapper.updateByExample(model, example);
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
