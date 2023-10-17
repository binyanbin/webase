package com.bin.api.dao.repository;

import com.bin.api.dao.mybatis.base.GuestMapper;
import com.bin.api.dao.mybatis.model.Guest;
import com.bin.api.dao.mybatis.model.GuestExample;
import com.bin.api.dao.repository.mapper.CommonSqlService;
import com.bin.webase.core.context.DoRepository;
import com.bin.webase.core.context.IRepository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@DoRepository
public class GuestRepository implements IRepository<Guest> {
    private static final String TABLE_NAME = "pl_guest";
    private final GuestMapper guestMapper;
    private final CommonSqlService commonMapper;

    public GuestRepository(GuestMapper guestMapper, CommonSqlService commonMapper) {
        this.guestMapper = guestMapper;
        this.commonMapper = commonMapper;
    }

    public Guest getByOpenId(String openId) {
        GuestExample example = new GuestExample();
        example.createCriteria().andOpenIdEqualTo(openId);
        List<Guest> guestList = guestMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(guestList)) {
            return null;
        } else {
            return guestList.get(0);
        }
    }

    public Guest getByPhone(String phone) {
        GuestExample example = new GuestExample();
        example.createCriteria().andPhoneEqualTo(phone);
        List<Guest> guestList = guestMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(guestList)) {
            return null;
        } else {
            return guestList.get(0);
        }
    }


    @Override
    public Guest getModel(Long id) {
        return guestMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Guest model) {
        guestMapper.insert(model);
    }

    @Override
    public void delete(Long id) {
        guestMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Guest model) {
        guestMapper.updateByPrimaryKey(model);
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
