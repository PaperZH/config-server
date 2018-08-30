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
     * 添加点赞课程记录
     */
    @Override
    public Result addPraiseCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId) {
        PraiseCourseDO praiseCourse = praiseCourseService.getByUserIdAndCourseId(userId, courseId);
        if(praiseCourse != null) {
            if(praiseCourse.getDelFlag() == 1){
                return Result.getSuccessResult("已点赞课程");
            } else {
                praiseCourse.setPublishDate(new Date());
                praiseCourse.setDelFlag(new Byte("1"));
                praiseCourseService.updateByPrimaryKeySelective(praiseCourse);
                return Result.getSuccessResult("添加点赞课程成功");
            }
        } else {
            praiseCourse = new PraiseCourseDO();
            praiseCourse.setUserId(userId);
            praiseCourse.setCourseId(courseId);
            praiseCourse.setPublishDate(new Date());
            praiseCourse.setDelFlag(new Byte("1"));
            int count = praiseCourseService.insert(praiseCourse);
            if (count > 0) {
                return Result.getSuccessResult("添加点赞课程成功");
            } else {
                return Result.getBusinessException("添加点赞课程失败", "-2");
            }
        }
    }

    /**
     * 删除点赞课程记录
     * @param userId 用户id
     * @parm courseId 课程id
     * @return
     */
    @Override
    public Result deletePraiseCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId) {
        PraiseCourseDO praiseCourse = praiseCourseService.getByUserIdAndCourseId(userId, courseId);
        if(praiseCourse == null) {
            return Result.getSuccessResult("没有点选该课程");
        } else {
            int count = praiseCourseService.deleteByPrimaryKey(praiseCourse.getId());
            if(count != 0) {
                return Result.getSuccessResult("删除点赞课程信息成功");
            } else {
                return Result.getBusinessException("删除点赞课程信息失败", "-2");
            }
        }
    }

    @Override
    public Result<Boolean> isPraisedCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId) {
        PraiseCourseDO praiseCourse = praiseCourseService.getByUserIdAndCourseId(userId, courseId);
        if(praiseCourse != null) {
            return Result.getSuccessResult(true);
        } else {
            return Result.getSuccessResult(false);
        }
    }


}
