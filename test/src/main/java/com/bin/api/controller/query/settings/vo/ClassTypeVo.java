package com.bin.api.controller.query.settings.vo;

import com.bin.api.dao.mybatis.model.ClassType;
import com.bin.webase.core.entity.statemachine.BizStateId;
import com.bin.webase.core.entity.statemachine.NormalStateMachine;
import lombok.Data;

@Data
public class ClassTypeVo {
    private Long id;
    private String name;
    private String description;
    private Integer statusId;
    private String statusName;

    public ClassTypeVo(ClassType classType) {
        this.setDescription(classType.getDescription());
        this.setName(classType.getName());
        this.setId(classType.getId());
        BizStateId status = NormalStateMachine.getBizState(classType.getStatusId());
        if (status != null) {
            this.setStatusId(status.getId());
            this.setStatusName(status.getName());
        }
    }
}
