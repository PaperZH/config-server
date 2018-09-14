package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.api.PraiseCourseApi;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.DO.PraiseCourseDO;
import com.ucar.qtcassist.course.service.CourseService;
import com.ucar.qtcassist.course.service.PraiseCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
@RequestMapping("/praiseCourse")
public class PraiseCourseController implements PraiseCourseApi {
    @Autowired
    private PraiseCourseService praiseCourseService;

    @Autowired
    private CourseService courseService;

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
                courseService.updatePraiseNum(courseId, +1);
                return Result.getSuccessResult("添加点赞课程成功");
                //更新course
            }
        } else {
            praiseCourse = new PraiseCourseDO();
            praiseCourse.setUserId(userId);
            praiseCourse.setCourseId(courseId);
            praiseCourse.setPublishDate(new Date());
            praiseCourse.setDelFlag(new Byte("1"));
            int count = praiseCourseService.insert(praiseCourse);
            courseService.updatePraiseNum(courseId, +1);
            //更新course
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
            return Result.getSuccessResult("没有点赞该课程");
        } else {
            int count = praiseCourseService.deleteByPrimaryKey(praiseCourse.getId());
            if(count > 0) {
                courseService.updatePraiseNum(courseId, -1);
                return Result.getSuccessResult("删除点赞课程信息成功");
            } else {
                return Result.getBusinessException("删除点赞课程信息失败", "-2");
            }
        }
    }

    /**
     * 查看用户是否已点赞该课程
     * @param userId 用户id
     * @param courseId 课程id
     * @return
     */
    @Override
    public Result<Boolean> isPraisedCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId) {
        PraiseCourseDO praiseCourse = praiseCourseService.getByUserIdAndCourseId(userId, courseId);
        if(praiseCourse != null && praiseCourse.getDelFlag() == 1) {
            return Result.getSuccessResult(true);
        } else {
            return Result.getSuccessResult(false);
        }
    }

}
