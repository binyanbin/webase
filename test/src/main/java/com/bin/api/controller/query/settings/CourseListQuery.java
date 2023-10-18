package com.bin.api.controller.query.settings;

import com.bin.api.controller.query.settings.vo.CourseVo;
import com.bin.api.dao.mybatis.model.ClassType;
import com.bin.api.dao.mybatis.model.Course;
import com.bin.api.dao.repository.view.BranchView;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.utils.CollectionTransferUtils;
import com.bin.webase.core.model.NoParam;
import com.bin.webase.core.query.Query;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseListQuery extends Query<List<CourseVo>, NoParam> {

    @Override
    protected List<CourseVo> getData(NoParam param) {
        WebSession webSession = getToken(WebSession.class);
        BranchView branchView = new BranchView(webSession.getBranchId());
        List<ClassType> classTypes = branchView.listClassType();
        Map<Long, ClassType> mapType = CollectionTransferUtils.toMap(classTypes, ClassType::getId);
        List<Course> courses = branchView.listCourse();
        List<CourseVo> result = Lists.newArrayList();
        for (Course course : courses) {
            result.add(new CourseVo(course, mapType));
        }
        return result;
    }
}
