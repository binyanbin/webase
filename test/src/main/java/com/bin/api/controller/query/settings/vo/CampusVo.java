package com.bin.api.controller.query.settings.vo;

import com.bin.api.dao.mybatis.model.Campus;
import com.bin.webase.core.entity.statemachine.BizStateId;
import com.bin.webase.core.entity.statemachine.NormalStateMachine;
import lombok.Data;

@Data
public class CampusVo {
    private Long id;
    private String name;
    private String address;
    private Integer statusId;
    private String statusName;

    public CampusVo(Campus campus) {
        this.setAddress(campus.getAddress());
        this.setId(campus.getId());
        this.setName(campus.getName());
        BizStateId bizState = NormalStateMachine.getBizState(campus.getStatusId());
        if (bizState != null) {
            this.setStatusId(bizState.getId());
            this.setStatusName(bizState.getName());
        }
    }
}
