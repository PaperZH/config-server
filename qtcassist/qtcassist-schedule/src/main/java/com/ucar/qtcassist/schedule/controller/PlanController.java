package com.ucar.qtcassist.schedule.controller;

import com.ucar.qtcassist.base.model.Result;
import com.ucar.qtcassist.course.model.CourseTypeDO;
import com.ucar.qtcassist.course.service.CourseTypeService;
import com.ucar.qtcassist.schedule.model.PlanDO;
import com.ucar.qtcassist.schedule.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
public class PlanController {

    @Autowired
    private PlanService planService;

    @Autowired
    private CourseTypeService courseTypeService;

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        int count = planService.deleteByPrimaryKey(id);
        if(count != 0) {
            return Result.getSuccessResult("删除培训计划信息成功");
        } else {
            return Result.getBusinessException("删除培训计划信息失败", "-2");
        }
    }

    @PostMapping("/add")
    public Result add(PlanDO plan) {
        int count = planService.insertSelective(plan);
        if(count != 0) {
            return Result.getSuccessResult("添加培训计划信息成功");
        } else {
            return Result.getBusinessException("添加培训计划信息失败", "-2");
        }
    }

    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id) {
        PlanDO plan = planService.selectByPrimaryKey(id);
        return Result.getSuccessResult(plan);
    }

    @GetMapping("/update")
    public Result update(PlanDO plan) {
        int count = planService.updateByPrimaryKeySelective(plan);
        if(count != 0) {
            return Result.getSuccessResult("更新培训计划信息成功");
        } else {
            return Result.getBusinessException("更新培训计划信息失败", "-2");
        }
    }

    @GetMapping("/getType/{id}")
    public CourseTypeDO getType(@PathVariable("id") Long id) {
        CourseTypeDO courseTypeDO = courseTypeService.selectByPrimaryKey(id);
        System.out.println(courseTypeDO);
        return  courseTypeDO;
    }
}
