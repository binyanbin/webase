package com.bin.api.controller.param.classroom;

import com.bin.webase.core.model.IParam;
import lombok.Data;

@Data
public class MemberRecordParam implements IParam {
    private Long memberId;
    private Integer coin;
    private Integer type;
    private String remark;

    @Override
    public void validate() {

    }
}
