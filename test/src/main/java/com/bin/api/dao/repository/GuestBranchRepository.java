package com.bin.api.dao.repository;

import com.bin.api.dao.mybatis.base.GuestBranchMapper;
import com.bin.api.dao.mybatis.model.GuestBranch;
import com.bin.api.dao.mybatis.model.GuestBranchExample;
import com.bin.webase.core.context.DoRepository;
import com.bin.webase.core.context.IRepository;
import com.bin.api.dao.repository.mapper.CommonSqlService;
import org.springframework.util.CollectionUtils;

import java.util.List;

@DoRepository
public class GuestBranchRepository implements IRepository<GuestBranch> {
    private static final String TABLE_NAME = "pl_guest_branch";
    private final GuestBranchMapper mapper;
    private final CommonSqlService commonMapper;

    public GuestBranchRepository(GuestBranchMapper mapper, CommonSqlService commonMapper) {
        this.mapper = mapper;
        this.commonMapper = commonMapper;
    }

    public List<GuestBranch> listByGuestId(Long guestId) {
        GuestBranchExample example = new GuestBranchExample();
        example.createCriteria().andGuestIdEqualTo(guestId);
        return mapper.selectByExample(example);
    }

    public GuestBranch getByGuestId(Long guestId, Long branchId) {
        GuestBranchExample example = new GuestBranchExample();
        example.createCriteria().andGuestIdEqualTo(guestId).andBranchIdEqualTo(branchId);
        List<GuestBranch> list = mapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public GuestBranch getModel(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(GuestBranch model) {
        mapper.insert(model);
    }

    @Override
    public void delete(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(GuestBranch model) {
        mapper.updateByPrimaryKey(model);
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
