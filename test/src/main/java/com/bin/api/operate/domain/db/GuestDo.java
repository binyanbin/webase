package com.bin.api.operate.domain.db;

import com.bin.api.dao.mybatis.model.Guest;
import com.bin.api.dao.repository.GuestRepository;
import com.bin.webase.core.context.WeContext;
import com.bin.webase.core.context.IRepository;
import com.bin.webase.core.entity.DbDomain;
import com.bin.webase.core.entity.IBranch;
import org.springframework.util.StringUtils;

public class GuestDo extends DbDomain<Guest> implements IBranch {

    public static GuestRepository REPOSITORY = WeContext.getRepository(GuestRepository.class);

    public GuestDo(Long id) {
        super(id);
    }

    public GuestDo(Guest model) {
        super(model);
    }

    @Override
    public IRepository<Guest> getRepository() {
        return REPOSITORY;
    }

    public GuestDo(String openId) {
        super(REPOSITORY.getByOpenId(openId));
    }

    @Override
    public Long getId() {
        return model.getId();
    }

    @Override
    public void setId(Long id) {
        model.setId(id);
    }

    public static GuestDo newInstance(String openId, String name, Long branchId) {
        Guest model = new Guest();
        model.setName(name);
        model.setOpenId(openId);
        model.setBranchId(branchId);
        return new GuestDo(model);
    }

    public boolean hasPhone() {
        return StringUtils.hasText(model.getPhone());
    }

    public void bind(String name, String phone) {
        model.setPhone(phone);
        model.setName(name);
    }

    public String getPhone() {
        return model.getPhone();
    }

    @Override
    public Long getBranchId() {
        return model.getBranchId();
    }

    @Override
    public boolean branchCache() {
        return false;
    }

    public void setBranchId(Long branchId) {
        model.setBranchId(branchId);
    }
}
