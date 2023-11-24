package com.bin.api.controller;

import com.bin.api.controller.query.settings.CampusListQuery;
import com.bin.api.controller.query.settings.ClassTypeListQuery;
import com.bin.api.controller.query.settings.CourseListQuery;
import com.bin.api.controller.param.CampusParam;
import com.bin.api.controller.param.ClassTypeParam;
import com.bin.api.controller.param.CourseParam;
import com.bin.api.controller.param.IdListParam;
import com.bin.api.controller.query.settings.vo.CampusVo;
import com.bin.api.controller.query.settings.vo.ClassTypeVo;
import com.bin.api.controller.query.settings.vo.CourseVo;
import com.bin.api.operate.setting.*;

import com.bin.webase.core.context.WebaseContext;
import com.bin.webase.core.query.DTO;
import com.bin.webase.core.query.NoResultDTO;
import com.bin.webase.core.query.DTOUtils;
import com.bin.webase.core.web.ApiMethodAttribute;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("settings")
public class SettingController {

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "campus", method = {RequestMethod.GET})
    public DTO<List<CampusVo>> listCampus() {
        return DTOUtils.success(WebaseContext.getBean(CampusListQuery.class).execute());
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "campus", method = {RequestMethod.POST})
    public NoResultDTO addCampus(@RequestBody CampusParam param) {
        WebaseContext.getBean(AddCampusOp.class).execute(param);
        return DTOUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "campus/enable", method = {RequestMethod.POST})
    public NoResultDTO enableCampus(@RequestBody IdListParam param) {
        WebaseContext.getBean(EnableCampusOp.class).execute(param);
        return DTOUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "campus/disable", method = {RequestMethod.POST})
    public NoResultDTO disableCampus(@RequestBody IdListParam param) {
        WebaseContext.getBean(DisableCampusOp.class).execute(param);
        return DTOUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "campus/delete", method = {RequestMethod.POST})
    public NoResultDTO deleteCampus(@RequestBody IdListParam param) throws Exception {
        WebaseContext.getBean(DeleteCampusOp.class).execute(param);
        return DTOUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "class/type", method = {RequestMethod.GET})
    public DTO<List<ClassTypeVo>> listClassType() {
        return DTOUtils.success(WebaseContext.getBean(ClassTypeListQuery.class).execute());
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "class/type", method = {RequestMethod.POST})
    public NoResultDTO addClassType(@RequestBody ClassTypeParam param) {
        WebaseContext.getBean(AddClassTypeOp.class).execute(param);
        return DTOUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "class/type/enable", method = {RequestMethod.POST})
    public NoResultDTO enableClassType(@RequestBody IdListParam param) {
        WebaseContext.getBean(EnableClassTypeOp.class).execute(param);
        return DTOUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "class/type/disable", method = {RequestMethod.POST})
    public NoResultDTO disableClassType(@RequestBody IdListParam param) {
        WebaseContext.getBean(DisableClassTypeOp.class).execute(param);
        return DTOUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "class/type/delete", method = {RequestMethod.POST})
    public NoResultDTO deleteClassType(@RequestBody IdListParam param) {
        WebaseContext.getBean(DeleteClassTypeOp.class).execute(param);
        return DTOUtils.success();
    }


    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "course", method = {RequestMethod.GET})
    public DTO<List<CourseVo>> listCourse() {
        return DTOUtils.success(WebaseContext.getBean(CourseListQuery.class).execute());
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "course", method = {RequestMethod.POST})
    public NoResultDTO addCourse(@RequestBody CourseParam param) {
        WebaseContext.getBean(AddCourseOp.class).execute(param);
        return DTOUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "course/enable", method = {RequestMethod.POST})
    public NoResultDTO enableCourse(@RequestBody IdListParam param) {
        WebaseContext.getBean(EnableCampusOp.class).execute(param);
        return DTOUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "course/disable", method = {RequestMethod.POST})
    public NoResultDTO disablecourse(@RequestBody IdListParam param) {
        WebaseContext.getBean(DisableCourseOp.class).execute(param);
        return DTOUtils.success();
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "course/delete", method = {RequestMethod.POST})
    public NoResultDTO deletecourse(@RequestBody IdListParam param) {
        WebaseContext.getBean(DeleteCourseOp.class).execute(param);
        return DTOUtils.success();
    }
}
