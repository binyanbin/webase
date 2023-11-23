package com.bin.api.operate.domain.db;

import com.bin.api.dao.mybatis.model.GuestBranch;
import com.bin.api.dao.repository.GuestBranchRepository;
import com.bin.webase.core.context.WeContext;
import com.bin.webase.core.context.IRepository;
import com.bin.webase.core.entity.DbDomain;
import com.bin.webase.core.entity.IBranch;

import java.util.Date;

public class GuestBranchDo extends DbDomain<GuestBranch> implements IBranch {

    public static GuestBranchRepository REPOSITORY = WeContext.getRepository(GuestBranchRepository.class);

    public GuestBranchDo(Long id) {
        super(id);
    }

    public GuestBranchDo(GuestBranch model) {
        super(model);
    }

    @Override
    public IRepository<GuestBranch> getRepository() {
        return REPOSITORY;
    }

    public static GuestBranchDo newInstance(Long guestId, Long branchId, Date now) {
        GuestBranch model = new GuestBranch();
        model.setBranchId(branchId);
        model.setGuestId(guestId);
        model.setCreatedTime(now);
        return new GuestBranchDo(model);
    }

    @Override
    public Long getId() {
        return model.getId();
    }

    @Override
    public void setId(Long id) {
        model.setId(id);
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
