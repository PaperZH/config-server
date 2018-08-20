package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.base.model.Result;
import com.ucar.qtcassist.course.model.UserCourseDO;
import com.ucar.qtcassist.course.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@RestController
@RequestMapping("/userCourse")
public class UserCourseController {

    @Autowired
    private UserCourseService userCourseService;

    /**
     * 删除用户课程记录
     * @param id 用户课程记录id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        int count = userCourseService.deleteByPrimaryKey(id);
        if(count != 0) {
            return Result.getSuccessResult("删除用户课程信息成功");
        } else {
            return Result.getBusinessException("删除用户课程信息失败", "-2");
        }
    }

    /**
     * 添加用户课程记录
     */
    @PostMapping("/add")
    public Result add(UserCourseDO userCourse) {
        userCourse.setPublishDate(new Date());
        int count = userCourseService.insert(userCourse);
        if(count != 0) {
            return Result.getSuccessResult("添加用户课程成功");
        } else {
            return Result.getBusinessException("添加用户课程失败", "-2");
        }
    }

    /**
     * 查询用户课程记录
     * @param id 用户课程记录id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id) {
        UserCourseDO userCourse = userCourseService.selectByPrimaryKey(id);
        return Result.getSuccessResult(userCourse);
    }

    /**
     * 更新用户课程记录
     * @param userCourse 用户课程记录对象
     * @return
     */
    @PostMapping("/update")
    public Result update(UserCourseDO userCourse) {
        int count = userCourseService.updateByPrimaryKeySelective(userCourse);
        if(count != 0) {
            return Result.getSuccessResult("更新用户课程成功");
        } else {
            return Result.getBusinessException("更新用户课程失败", "-2");
        }
    }

}
