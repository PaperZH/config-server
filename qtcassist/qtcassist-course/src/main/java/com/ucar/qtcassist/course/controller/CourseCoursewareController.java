package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.course.model.CourseCoursewareDO;
import com.ucar.qtcassist.course.service.CourseCoursewareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courseCourseware")
public class CourseCoursewareController {

    @Autowired
    private CourseCoursewareService courseCoursewareService;

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        int count = courseCoursewareService.deleteByPrimaryKey(id);
        if(count != 0) {
            return Result.getSuccessResult("删除课程课件关联信息成功");
        } else {
            return Result.getBusinessException("删除课程课件关联信息失败", "-2");
        }
    }

    @PostMapping("/add")
    public Result add(@RequestBody CourseCoursewareDO courseCourseware) {
        int count = courseCoursewareService.insertSelective(courseCourseware);
        if(count != 0) {
            return Result.getSuccessResult("添加课程课件关联信息成功");
        } else {
            return Result.getBusinessException("添加课程课件关联信息失败", "-2");
        }
    }

    @GetMapping("/get/{id}")
    public Result<CourseCoursewareDO> get(@PathVariable("id") Long id) {
        CourseCoursewareDO courseCoursewareDO = courseCoursewareService.selectByPrimaryKey(id);
        return Result.getSuccessResult(courseCoursewareDO);
    }

    @PostMapping("/update")
    public Result update(@RequestBody CourseCoursewareDO courseCourseware) {
        int count = courseCoursewareService.updateByPrimaryKeySelective(courseCourseware);
        if(count != 0) {
            return Result.getSuccessResult("更新课程课件关联信息成功");
        } else {
            return Result.getBusinessException("更新课程课件关联信息失败", "-2");
        }
    }

}
