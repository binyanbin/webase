package com.bin.api.controller.param.classroom;


import com.bin.webase.core.operate.IParam;
import com.bin.webase.exception.ErrorCheck;
import lombok.Data;

import java.util.Calendar;
import java.util.List;

@Data
public class ClassroomParam implements IParam {
    private Long teacherId;
    private Long courseId;
    private Long campusId;
    private Integer conditionCount;
    private Integer maxCount;
    private Integer returnCondition;
    private Integer coin;
    private List<TimeParam> times;


    @Override
    public void validate() {
        Calendar calendar = Calendar.getInstance();
        Integer day = calendar.get(Calendar.DAY_OF_YEAR);
        for (TimeParam param : times) {
            Calendar c = Calendar.getInstance();
            c.setTime(param.getDate());
            Integer d = c.get(Calendar.DAY_OF_YEAR);
            ErrorCheck.checkArgument(d > day, "时间必须大于今天");
        }
    }
}
