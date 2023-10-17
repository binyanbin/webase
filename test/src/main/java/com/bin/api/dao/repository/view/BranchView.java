package com.bin.api.dao.repository.view;

import com.bin.api.operate.domain.db.BranchDo;
import com.bin.api.operate.domain.db.CampusDo;
import com.bin.api.operate.domain.db.ClassTypeDo;
import com.bin.api.operate.domain.db.CourseDo;
import com.bin.api.dao.mybatis.model.*;
import com.bin.webase.core.query.View;

import java.util.List;


public class BranchView extends View<Branch> {
    private List<Campus> campuses;
    private List<ClassType> classTypes;
    private List<Course> courses;

    public BranchView(Long branchId) {
        super(branchId, BranchDo.REPOSITORY);
    }

    public List<Campus> listCampus() {
        if (campuses == null) {
            campuses = CampusDo.REPOSITORY.listByBranchId(root.getId());
        }
        return campuses;
    }

    public List<ClassType> listClassType() {
        if (classTypes == null) {
            classTypes = ClassTypeDo.REPOSITORY.listByBranchId(root.getId());
        }
        return classTypes;
    }

    public List<Course> listCourse() {
        if (courses == null) {
            courses = CourseDo.REPOSITORY.listByBranchId(root.getId());
        }
        return courses;
    }



}
