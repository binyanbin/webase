package com.bin.api.operate.domain.cache;

import com.bin.webase.core.entity.UniqueId;
import lombok.Data;

@Data
public class ActiveBranch implements UniqueId {
    public static String PREFIX = "active_branch_";
    private Long branchId;
    private String code;

    public ActiveBranch(Long branchId, String code) {
        this.branchId = branchId;
        this.code = code;
    }
    
    @Override
    public String getUniqueId() {
        return PREFIX + branchId;
    }
}
