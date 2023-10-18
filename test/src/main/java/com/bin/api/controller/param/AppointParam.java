package com.bin.api.controller.param;

import com.bin.webase.core.model.IParam;
import com.bin.webase.exception.ErrorCheck;
import lombok.Data;

@Data
public class AppointParam implements IParam {

    private Long memberId;
    private Long classroomId;

    @Override
    public void validate() {
        ErrorCheck.checkNotNull(memberId, "会员不能为空");
        ErrorCheck.checkNotNull(classroomId, "课程不能为空");
    }
}
