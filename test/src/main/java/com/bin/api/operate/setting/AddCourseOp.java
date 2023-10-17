package com.bin.api.operate.setting;

import com.bin.api.controller.param.CourseParam;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.operate.domain.db.CourseDo;
import com.bin.api.web.base.OperateDef;
import com.bin.api.web.base.FunctionDef;
import com.bin.webase.core.entity.FunctionId;
import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import com.bin.webase.core.operate.OperateId;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AddCourseOp extends Operator<CourseParam> {
    @Override
    protected Result dispose(CourseParam param) {
        WebSession webSession = getToken(WebSession.class);
        CourseDo courseDo = CourseDo.newInstance(param, webSession.getBranchId(), webSession.getEmployeeId(), getTime());
        updateState(courseDo);
        save(courseDo);
        saveBranchLog(courseDo);
        return Result.success(courseDo.getId());
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.ADD_COURSE;
    }


    @Override
    public List<FunctionId> getFunction() {
        return Lists.newArrayList(FunctionDef.COURSE_MANAGE);
    }
}
