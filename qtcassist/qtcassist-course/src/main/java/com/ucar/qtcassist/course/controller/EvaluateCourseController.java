package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.api.EvaluateCourseApi;
import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.common.PageResult;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.DO.EvaluateCourseDO;
import com.ucar.qtcassist.course.service.EvaluateCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/evaluateCourse")
public class EvaluateCourseController implements EvaluateCourseApi {

    @Autowired
    private EvaluateCourseService evaluateCourseService;

    /**
     * 删除评价课程记录
     * @param evaluateCourseId 评价课程记录id
     * @return
     */
    @Override
    public Result deleteEvaluateCourse(@PathVariable("evaluateCourseId") Long evaluateCourseId) {
        int count = evaluateCourseService.deleteByPrimaryKey(evaluateCourseId);
        if(count != 0) {
            return Result.getSuccessResult("删除评价课程信息成功");
        } else {
            return Result.getBusinessException("删除评价课程信息失败", "");
        }
    }

    /**
     * 添加评价课程记录
     */
    @Override
    public Result addEvaluateCourse(@RequestBody EvaluateCourseDO evaluateCourse) {
        evaluateCourse.setPublishDate(new Date());
        int count = evaluateCourseService.insert(evaluateCourse);
        if(count != 0) {
            return Result.getSuccessResult("添加评价课程成功");
        } else {
            return Result.getBusinessException("添加评价课程失败", "");
        }
    }

    /**
     * 查询评价课程记录
     * @param evaluateCourseId 评价课程记录id
     * @return
     */
    @Override
    public Result<EvaluateCourseDO> getEvaluateCourse(@PathVariable("evaluateCourseId") Long evaluateCourseId) {
        EvaluateCourseDO evaluateCourse = evaluateCourseService.selectByPrimaryKey(evaluateCourseId);
        return Result.getSuccessResult(evaluateCourse);
    }

    @Override
    public Result<Page<EvaluateCourseDO>> getEvaluateCourseList(Long courseId) {
        List<EvaluateCourseDO> evaluateCourseDOList = evaluateCourseService.getListByCourseId(courseId);
        Integer total = evaluateCourseService.getTotalByCourseId(courseId);
        return PageResult.getSuccessResult(evaluateCourseDOList, total);
    }

    /**
     * 更新评价课程记录
     * @param evaluateCourse 评价课程记录对象
     * @return
     */
    @Override
    public Result updateEvaluateCourse(@RequestBody EvaluateCourseDO evaluateCourse) {
        int count = evaluateCourseService.updateByPrimaryKeySelective(evaluateCourse);
        if(count != 0) {
            return Result.getSuccessResult("更新评价课程成功");
        } else {
            return Result.getBusinessException("更新评价课程失败", "");
        }
    }

}
