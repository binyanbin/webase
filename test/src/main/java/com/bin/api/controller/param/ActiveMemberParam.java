package com.bin.api.controller.param;

import com.bin.webase.core.model.IParam;
import lombok.Data;

@Data
public class ActiveMemberParam implements IParam {
    private Long guestId;
    private Long branchId;

    @Override
    public void validate() {
        
    }
}
