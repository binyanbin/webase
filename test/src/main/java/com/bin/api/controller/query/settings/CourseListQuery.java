package com.bin.api.controller.query.settings;

import com.bin.api.controller.query.settings.vo.CourseVo;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.dao.mybatis.model.ClassType;
import com.bin.api.dao.mybatis.model.Course;
import com.bin.api.dao.repository.view.BranchView;
import com.bin.api.utils.CollectionTransferUtils;
import com.bin.webase.core.query.FunctionQuery;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

public class CourseListQuery extends FunctionQuery<List<CourseVo>> {
    public CourseListQuery() {
        super();
        WebSession webSession = getToken(WebSession.class);
        BranchView branchView = new BranchView(webSession.getBranchId());
        List<ClassType> classTypes = branchView.listClassType();
        Map<Long, ClassType> mapType = CollectionTransferUtils.toMap(classTypes, ClassType::getId);
        List<Course> courses = branchView.listCourse();
        List<CourseVo> result = Lists.newArrayList();
        for (Course course : courses) {
            result.add(new CourseVo(course, mapType));
        }
        setData(result);
    }
}
