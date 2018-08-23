package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.course.model.EvaluateCourseDO;
import com.ucar.qtcassist.course.service.EvaluateCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/evaluateCourse")
public class EvaluateCourseController {

    @Autowired
    private EvaluateCourseService evaluateCourseService;

    /**
     * 删除评价课程记录
     * @param id 评价课程记录id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        int count = evaluateCourseService.deleteByPrimaryKey(id);
        if(count != 0) {
            return Result.getSuccessResult("删除评价课程信息成功");
        } else {
            return Result.getBusinessException("删除评价课程信息失败", "-2");
        }
    }

    /**
     * 添加评价课程记录
     */
    @PostMapping("/add")
    public Result add(@RequestBody EvaluateCourseDO evaluateCourse) {
        evaluateCourse.setPublishDate(new Date());
        int count = evaluateCourseService.insert(evaluateCourse);
        if(count != 0) {
            return Result.getSuccessResult("添加评价课程成功");
        } else {
            return Result.getBusinessException("添加评价课程失败", "-2");
        }
    }

    /**
     * 查询评价课程记录
     * @param id 评价课程记录id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result<EvaluateCourseDO> get(@PathVariable("id") Long id) {
        EvaluateCourseDO evaluateCourse = evaluateCourseService.selectByPrimaryKey(id);
        return Result.getSuccessResult(evaluateCourse);
    }

    /**
     * 更新评价课程记录
     * @param evaluateCourse 评价课程记录对象
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody EvaluateCourseDO evaluateCourse) {
        int count = evaluateCourseService.updateByPrimaryKeySelective(evaluateCourse);
        if(count != 0) {
            return Result.getSuccessResult("更新评价课程成功");
        } else {
            return Result.getBusinessException("更新评价课程失败", "-2");
        }
    }

}
