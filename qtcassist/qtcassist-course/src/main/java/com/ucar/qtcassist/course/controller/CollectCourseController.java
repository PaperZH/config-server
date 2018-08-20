package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.base.model.Result;
import com.ucar.qtcassist.course.model.CollectCourseDO;
import com.ucar.qtcassist.course.service.CollectCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Date;

@RestController
@RequestMapping("/collectCourse")
public class CollectCourseController {

    @Autowired
    private CollectCourseService collectCourseService;

    /**
     * 删除收藏课程记录
     * @param id 收藏课程记录id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        int count = collectCourseService.deleteByPrimaryKey(id);
        if(count != 0) {
            return Result.getSuccessResult("删除收藏课程信息成功");
        } else {
            return Result.getBusinessException("删除收藏课程信息失败", "-2");
        }
    }

    /**
     * 添加收藏课程记录
     */
    @PostMapping("/add")
    public Result add(CollectCourseDO collectCourse) {
        collectCourse.setPublishDate(new Date());
        int count = collectCourseService.insert(collectCourse);
        if(count != 0) {
            return Result.getSuccessResult("添加收藏课程成功");
        } else {
            return Result.getBusinessException("添加收藏课程失败", "-2");
        }
    }

    /**
     * 查询收藏课程记录
     * @param id 收藏课程记录id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id) {
        CollectCourseDO collectCourse = collectCourseService.selectByPrimaryKey(id);
        return Result.getSuccessResult(collectCourse);
    }

    /**
     * 更新收藏课程记录
     * @param collectCourse 收藏课程记录对象
     * @return
     */
    @PostMapping("/update")
    public Result update(CollectCourseDO collectCourse) {
        int count = collectCourseService.updateByPrimaryKeySelective(collectCourse);
        if(count != 0) {
            return Result.getSuccessResult("更新收藏课程成功");
        } else {
            return Result.getBusinessException("更新收藏课程失败", "-2");
        }
    }
}
