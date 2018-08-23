package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.course.model.CourseTypeDO;
import com.ucar.qtcassist.course.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courseType")
public class CourseTypeController {

    @Autowired
    private CourseTypeService courseTypeService;

    /**
     * 根据课程类型id删除课程类型
     * @param id 课程类型id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        Result result = null;
        int count = courseTypeService.deleteByPrimaryKey(id);
        Result.getSuccessResult(null);
        if(count > 0) {
            result = Result.getSuccessResult(null);
        } else {
            result = Result.getBusinessException("1001", "failed");
        }
        return result;
    }

    /**
     * 添加课程类型
     * @param courseType 课程类型对象
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody CourseTypeDO courseType){
        Result result = null;
        int count = courseTypeService.insert(courseType);
        if(count > 0) {
            result = Result.getSuccessResult(null);
        } else {
            result = Result.getBusinessException("1001", "failed");
        }
        return result;
    }

    /**
     * 根据课程类型id得到课程类型对象
     * @param id 课程类型id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result<CourseTypeDO> get(@PathVariable("id") Long id) {
        Result result = null;
        CourseTypeDO courseType = courseTypeService.selectByPrimaryKey(id);
        if(courseType != null) {
            result = Result.getSuccessResult(courseType);
        } else {
            result = Result.getBusinessException("1001", " failed");
        }
        return result;
    }

    /**
     * 更新课程类型
     * @param courseType 课程类型对象
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody CourseTypeDO courseType) {
        Result result = null;
        int count = courseTypeService.updateByPrimaryKey(courseType);
        if(count > 0) {
            result = Result.getSuccessResult(null);
        } else {
            result = Result.getBusinessException("1001", " failed");
        }
        return result;
    }

}
