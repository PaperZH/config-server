package com.ucar.qtcassist.schedule.controller;

import com.ucar.qtcassist.base.model.Result;
import com.ucar.qtcassist.api.model.CourseDO;
import com.ucar.qtcassist.course.service.CourseService;
import com.ucar.qtcassist.schedule.model.CoursePlanDO;
import com.ucar.qtcassist.schedule.service.CoursePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coursePlan")
public class CoursePlanController {

    @Autowired
    private CoursePlanService coursePlanService;

    @Autowired
    private CourseService courseService;

    /**
     * 删除课程计划关系
     * @param id 计划课程id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        int count = coursePlanService.deleteByPrimaryKey(id);
        if(count != 0) {
            return Result.getSuccessResult("删除课程计划关联信息成功");
        } else {
            return Result.getBusinessException("删除课程计划关联信息失败", "-2");
        }
    }

    /**
     * 添加课程计划关系
     * @param coursePlan 课程计划对象
     * @return
     */
    @PostMapping("/add")
    public Result add(CoursePlanDO coursePlan) {
        int count = coursePlanService.insertSelective(coursePlan);
        if(count != 0) {
            return Result.getSuccessResult("添加课程课件关联信息成功");
        } else {
            return Result.getBusinessException("添加课程课件关联信息失败", "-2");
        }
    }

    /**
     * 查询课程计划关系
     * @param id 计划课程id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id) {
        CoursePlanDO coursePlanDO = coursePlanService.selectByPrimaryKey(id);
        return Result.getSuccessResult(coursePlanDO);
    }

    /**
     * 更新课程计划关系
     * @param coursePlan 计划课程对象
     * @return
     */
    @PostMapping("/update")
    public Result update(CoursePlanDO coursePlan) {
        int count = coursePlanService.updateByPrimaryKeySelective(coursePlan);
        if(count != 0) {
            return Result.getSuccessResult("更新课程课件关联信息成功");
        } else {
            return Result.getBusinessException("更新课程课件关联信息失败", "-2");
        }
    }

    @GetMapping("/getCourse/{id}")
    public CourseDO getCourse(@PathVariable("id") Long id) {
        CourseDO course = courseService.selectByPrimaryKey(id);
        return course;
    }
}
