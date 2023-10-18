package com.bin.api.operate.method;

import com.bin.api.operate.domain.db.GuestBranchDo;
import com.bin.api.dao.mybatis.model.GuestBranch;
import com.bin.webase.core.operate.Method;
import com.bin.webase.core.model.OperateId;


import java.util.Date;
import java.util.List;

public class AddGuestBranch extends Method {
    private Long guestId;
    private Long branchId;


    public AddGuestBranch(Long guestId, Long branchId, OperateId operateId) {
        super(operateId);
        this.branchId = branchId;
        this.guestId = guestId;
    }

    @Override
    public void commit(Date now) {
        List<GuestBranch> guestBranches = GuestBranchDo.REPOSITORY.listByGuestId(guestId);
        boolean find = false;
        for (GuestBranch guestBranch : guestBranches) {
            if (guestBranch.getBranchId().equals(branchId)) {
                find = true;
                break;
            }
        }
        if (!find) {
            GuestBranchDo userBranch = GuestBranchDo.newInstance(guestId, branchId, now);
            save(userBranch);
        }
    }
}
