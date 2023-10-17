package com.bin.api.controller.query.settings.vo;

import com.bin.api.operate.domain.db.CourseDo;
import com.bin.api.dao.mybatis.model.ClassType;
import com.bin.api.dao.mybatis.model.Course;
import com.bin.webase.core.entity.statemachine.BizStateId;
import lombok.Data;

import java.util.Map;

@Data
public class CourseVo {
    private Long id;
    private String name;
    private String period;
    private String introduce;
    private String needToKnow;
    private Long classTypeId;
    private String classTypeName;
    private Integer statusId;
    private String statusName;

    public CourseVo(Course course, Map<Long, ClassType> mapType) {
        this.setId(course.getId());
        this.setIntroduce(course.getIntroduce());
        this.setName(course.getName());
        this.setPeriod(course.getPeriod());
        this.setNeedToKnow(course.getNeedToKnow());
        BizStateId status = CourseDo.STATE_MACHINE.parse(this.getStatusId());
        if (status != null) {
            this.setStatusId(status.getId());
            this.setStatusName(status.getName());
        }
        ClassType classType = mapType.get(course.getClassTypeId());
        if (classType != null) {
            this.setClassTypeId(classType.getId());
            this.setClassTypeName(classType.getName());
        }
    }
}
