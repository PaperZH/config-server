package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.api.CourseTypeApi;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.DO.CourseTypeDO;
import com.ucar.qtcassist.course.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courseType")
public class CourseTypeController implements CourseTypeApi {

    @Autowired
    private CourseTypeService courseTypeService;

    /**
     * 根据课程类型id删除课程类型
     * @param courseTypeId 课程类型id
     * @return
     */
    @Override
    public Result deleteCourseType(@PathVariable("courseTypeId") Long courseTypeId) {
        int count = courseTypeService.deleteByPrimaryKey(courseTypeId);
        Result.getSuccessResult(null);
        if(count > 0) {
            return Result.getSuccessResult("删除课程类型成功");
        } else {
            return Result.getBusinessException("删除课程类型失败","");
        }
    }

    /**
     * 添加课程类型
     * @param courseType 课程类型对象
     * @return
     */
    @Override
    public Result addCourseType(@RequestBody CourseTypeDO courseType){
        int count = courseTypeService.insert(courseType);
        if(count > 0) {
            return Result.getSuccessResult("添加课程类型成功");
        } else {
            return Result.getBusinessException("添加课程类型失败","");
        }
    }

    /**
     * 根据课程类型id得到课程类型对象
     * @param courseTypeId 课程类型id
     * @return
     */
    @Override
    public Result<CourseTypeDO> getCourseType(@PathVariable("courseTypeId") Long courseTypeId) {
        CourseTypeDO courseType = courseTypeService.selectByPrimaryKey(courseTypeId);
        if(courseType != null) {
            return Result.getSuccessResult(courseType);
        } else {
            return Result.getBusinessException("查询课程类型失败","");
        }
    }

    /**
     * 查询所有的课程类型对象
     * @return
     */
    @Override
    public Result<List<CourseTypeDO>> getCourseTypeList() {
        List<CourseTypeDO> courseTypeDOList = courseTypeService.getAllCourseType();
        return Result.getSuccessResult(courseTypeDOList);
    }

    /**
     * 更新课程类型
     * @param courseType 课程类型对象
     * @return
     */
    @Override
    public Result updateCourseType(@RequestBody CourseTypeDO courseType) {
        int count = courseTypeService.updateByPrimaryKey(courseType);
        if(count > 0) {
            return Result.getSuccessResult("更新课程类型成功");
        } else {
            return Result.getBusinessException("更新课程类型失败","");
        }
    }

}
