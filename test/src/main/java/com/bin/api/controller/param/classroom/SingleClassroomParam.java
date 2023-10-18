package com.bin.api.controller.param.classroom;

import com.bin.webase.core.model.IParam;
import com.bin.webase.exception.ErrorCheck;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
public class SingleClassroomParam implements IParam {
    private Long teacherId;
    private Long courseId;
    private Long campusId;
    private Integer conditionCount;
    private Integer maxCount;
    private Integer returnCondition;
    private Date date;
    private Integer timeSpan;
    private Integer coin;

    @Override
    public void validate() {
        Calendar calendar = Calendar.getInstance();
        Integer day = calendar.get(Calendar.DAY_OF_YEAR);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Integer d = c.get(Calendar.DAY_OF_YEAR);
        ErrorCheck.checkArgument(d > day, "时间必须大于今天");
    }
}
