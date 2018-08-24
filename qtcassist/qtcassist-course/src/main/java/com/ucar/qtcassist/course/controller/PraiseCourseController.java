package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.api.PraiseCourseApi;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.DO.PraiseCourseDO;
import com.ucar.qtcassist.course.service.PraiseCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/praiseCourse")
public class PraiseCourseController implements PraiseCourseApi {
    @Autowired
    private PraiseCourseService praiseCourseService;

    /**
     * 删除点赞课程记录
     * @param praiseCourseId 点赞课程记录id
     * @return
     */
    @Override
    public Result deletePraiseCourse(@PathVariable("praiseCourseId") Long praiseCourseId) {
        int count = praiseCourseService.deleteByPrimaryKey(praiseCourseId);
        if(count != 0) {
            return Result.getSuccessResult("删除点赞课程信息成功");
        } else {
            return Result.getBusinessException("删除点赞课程信息失败", "-2");
        }
    }

    /**
     * 添加点赞课程记录
     */
    @Override
    public Result addPraiseCourse(@RequestBody PraiseCourseDO praiseCourse) {
        praiseCourse.setPublishDate(new Date());
        int count = praiseCourseService.insert(praiseCourse);
        if(count != 0) {
            return Result.getSuccessResult("添加点赞课程成功");
        } else {
            return Result.getBusinessException("添加点赞课程失败", "-2");
        }
    }
}
