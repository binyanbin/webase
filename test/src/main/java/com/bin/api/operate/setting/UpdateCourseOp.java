package com.bin.api.operate.setting;

import com.bin.api.controller.param.CourseParam;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.operate.domain.db.CourseDo;
import com.bin.api.web.base.OperateDef;
import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import com.bin.webase.core.operate.OperateId;
import org.springframework.stereotype.Service;

@Service
public class UpdateCourseOp extends Operator<CourseParam> {
    @Override
    protected Result dispose(CourseParam param) {
        WebSession webSession = getToken(WebSession.class);
        Long id = param.getId();
        CourseDo course = new CourseDo(id);
        updateState(course);
        course.update(param, webSession.getEmployeeId(), getTime());
        updateState(course);
        save(course);
        saveBranchLog(course);
        return Result.success();
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.UPDATE_COURSE;
    }
}
