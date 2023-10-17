package com.bin.api.controller;

import com.bin.api.controller.query.settings.CampusListQuery;
import com.bin.api.controller.query.settings.ClassTypeListQuery;
import com.bin.api.controller.query.settings.CourseListQuery;
import com.bin.api.controller.param.CampusParam;
import com.bin.api.controller.param.ClassTypeParam;
import com.bin.api.controller.param.CourseParam;
import com.bin.api.controller.param.IdListParam;
import com.bin.api.operate.setting.*;

import com.bin.webase.core.context.WeContext;
import com.bin.webase.core.query.NoResult;
import com.bin.webase.core.query.QueryUtils;
import com.bin.webase.core.web.ApiMethodAttribute;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("settings")
public class SettingController {

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "campus", method = {RequestMethod.GET})
    public CampusListQuery listCampus() {
        return new CampusListQuery();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "campus", method = {RequestMethod.POST})
    public NoResult addCampus(@RequestBody CampusParam param) {
        WeContext.getBean(AddCampusOp.class).execute(param);
        return QueryUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "campus/enable", method = {RequestMethod.POST})
    public NoResult enableCampus(@RequestBody IdListParam param) {
        WeContext.getBean(EnableCampusOp.class).execute(param);
        return QueryUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "campus/disable", method = {RequestMethod.POST})
    public NoResult disableCampus(@RequestBody IdListParam param) {
        WeContext.getBean(DisableCampusOp.class).execute(param);
        return QueryUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "campus/delete", method = {RequestMethod.POST})
    public NoResult deleteCampus(@RequestBody IdListParam param) throws Exception {
        WeContext.getBean(DeleteCampusOp.class).execute(param);
        return QueryUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "class/type", method = {RequestMethod.GET})
    public ClassTypeListQuery listClassType() {
        return new ClassTypeListQuery();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "class/type", method = {RequestMethod.POST})
    public NoResult addClassType(@RequestBody ClassTypeParam param) {
        WeContext.getBean(AddClassTypeOp.class).execute(param);
        return QueryUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "class/type/enable", method = {RequestMethod.POST})
    public NoResult enableClassType(@RequestBody IdListParam param) {
        WeContext.getBean(EnableClassTypeOp.class).execute(param);
        return QueryUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "class/type/disable", method = {RequestMethod.POST})
    public NoResult disableClassType(@RequestBody IdListParam param) {
        WeContext.getBean(DisableClassTypeOp.class).execute(param);
        return QueryUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "class/type/delete", method = {RequestMethod.POST})
    public NoResult deleteClassType(@RequestBody IdListParam param) {
        WeContext.getBean(DeleteClassTypeOp.class).execute(param);
        return QueryUtils.success();
    }


    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "course", method = {RequestMethod.GET})
    public CourseListQuery listCourse() {
        return new CourseListQuery();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "course", method = {RequestMethod.POST})
    public NoResult addCourse(@RequestBody CourseParam param) {
        WeContext.getBean(AddCourseOp.class).execute(param);
        return QueryUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "course/enable", method = {RequestMethod.POST})
    public NoResult enableCourse(@RequestBody IdListParam param) {
        WeContext.getBean(EnableCampusOp.class).execute(param);
        return QueryUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "course/disable", method = {RequestMethod.POST})
    public NoResult disablecourse(@RequestBody IdListParam param) {
        WeContext.getBean(DisableCourseOp.class).execute(param);
        return QueryUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "course/delete", method = {RequestMethod.POST})
    public NoResult deletecourse(@RequestBody IdListParam param) {
        WeContext.getBean(DeleteCourseOp.class).execute(param);
        return QueryUtils.success();
    }
}
