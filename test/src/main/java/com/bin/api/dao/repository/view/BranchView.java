package com.bin.api.dao.repository.view;

import com.bin.api.operate.domain.db.BranchDo;
import com.bin.api.operate.domain.db.CampusDo;
import com.bin.api.operate.domain.db.ClassTypeDo;
import com.bin.api.operate.domain.db.CourseDo;
import com.bin.api.dao.mybatis.model.*;
import com.bin.webase.core.query.ModelView;

import java.util.List;


public class BranchView extends ModelView<Branch> {
    private List<Campus> campuses;
    private List<ClassType> classTypes;
    private List<Course> courses;

    public BranchView(Long branchId) {
        super(branchId, BranchDo.REPOSITORY);
    }

    public List<Campus> listCampus() {
        if (campuses == null) {
            campuses = CampusDo.REPOSITORY.listByBranchId(model.getId());
        }
        return campuses;
    }

    public List<ClassType> listClassType() {
        if (classTypes == null) {
            classTypes = ClassTypeDo.REPOSITORY.listByBranchId(model.getId());
        }
        return classTypes;
    }

    public List<Course> listCourse() {
        if (courses == null) {
            courses = CourseDo.REPOSITORY.listByBranchId(model.getId());
        }
        return courses;
    }


}
