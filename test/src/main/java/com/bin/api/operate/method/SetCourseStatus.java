package com.bin.api.operate.method;

import com.bin.api.operate.domain.db.CourseDo;
import com.bin.api.dao.mybatis.model.Course;
import com.bin.webase.core.operate.Method;
import com.bin.webase.core.model.OperateId;


import java.util.Date;
import java.util.List;

public class SetCourseStatus extends Method {
    private List<Course> courses;
    private Long employeeId;

    public SetCourseStatus(List<Long> ids, Long employeeId, OperateId operateId) {
        super(operateId);
        this.courses = CourseDo.REPOSITORY.listByIds(ids);
        this.employeeId = employeeId;
    }

    @Override
    public void commit(Date now) {
        for (Course course : courses) {
            CourseDo courseDo = new CourseDo(course);
            courseDo.updateState(employeeId, now);
            updateState(courseDo);
            save(courseDo);
            saveBranchLog(operateId, courseDo);
        }
    }
}
