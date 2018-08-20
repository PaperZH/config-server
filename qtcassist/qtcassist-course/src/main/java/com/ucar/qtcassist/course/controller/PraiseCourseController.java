package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.base.model.Result;
import com.ucar.qtcassist.course.model.PraiseCourseDO;
import com.ucar.qtcassist.course.service.PraiseCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/praiseCourse")
public class PraiseCourseController {
    @Autowired
    private PraiseCourseService praiseCourseService;

    /**
     * 删除点赞课程记录
     * @param id 点赞课程记录id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        int count = praiseCourseService.deleteByPrimaryKey(id);
        if(count != 0) {
            return Result.getSuccessResult("删除点赞课程信息成功");
        } else {
            return Result.getBusinessException("删除点赞课程信息失败", "-2");
        }
    }

    /**
     * 添加点赞课程记录
     */
    @PostMapping("/add")
    public Result add(PraiseCourseDO praiseCourse) {
        praiseCourse.setPublishDate(new Date());
        int count = praiseCourseService.insert(praiseCourse);
        if(count != 0) {
            return Result.getSuccessResult("添加点赞课程成功");
        } else {
            return Result.getBusinessException("添加点赞课程失败", "-2");
        }
    }

    /**
     * 查询点赞课程记录
     * @param id 点赞课程记录id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id) {
        PraiseCourseDO praiseCourse = praiseCourseService.selectByPrimaryKey(id);
        return Result.getSuccessResult(praiseCourse);
    }

    /**
     * 更新点赞课程记录
     * @param praiseCourse 点赞课程记录对象
     * @return
     */
    @PostMapping("/update")
    public Result update(PraiseCourseDO praiseCourse) {
        int count = praiseCourseService.updateByPrimaryKeySelective(praiseCourse);
        if(count != 0) {
            return Result.getSuccessResult("更新点赞课程成功");
        } else {
            return Result.getBusinessException("更新点赞课程失败", "-2");
        }
    }
}
