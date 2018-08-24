package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.api.UserCourseApi;
import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.common.PageResult;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.DO.CourseDO;
import com.ucar.qtcassist.api.model.DO.UserCourseDO;
import com.ucar.qtcassist.course.service.CourseService;
import com.ucar.qtcassist.course.service.UserCourseService;
import com.ucar.qtcassist.api.model.VO.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/userCourse")
public class UserCourseController implements UserCourseApi {

    private static final Logger logger = LoggerFactory.getLogger(UserCourseController.class);

    @Autowired
    private UserCourseService userCourseService;

    @Autowired
    private CourseService courseService;

    /**
     * 根据课程名字或发布时间区间获取发布的课程分页列表
     * @param query (long userId, String courseName,Date startTime, Date endTime, int currentPage, int pageSize)
     * @return
     */
    @Override
    public Result<Page<CourseDO>> getUserCourseList(@RequestBody Query query) {
        Long userId = query.getUserId();
        Integer currentPage = query.getCurrentPage();
        Integer pageSize = query.getPageSize();

        Integer startIndex = (currentPage - 1) * pageSize;
        List<CourseDO> courseDOList = null;
        List<Long> courseIdList = null;
        Integer total = null;
        if(query.getCourseName() != null) {
            courseIdList = userCourseService.selectCourseIdListByUserId(userId);
            String courseName = query.getCourseName();
            courseDOList = courseService.selectListByCourseName(courseIdList, courseName, startIndex, pageSize);
            total = courseService.getTotalByCourseName(courseIdList, courseName);
        } else {
            Date startTime = query.getStartTime();
            Date endTime = query.getEndTime();
            courseIdList = userCourseService.selectCourseIdListByDate(userId, startTime, endTime);
            courseDOList = courseService.selectListById(courseIdList, startIndex, pageSize);
            total = courseService.getTotalById(courseIdList);
        }
        return PageResult.getSuccessResult(courseDOList, total);
    }

    /**
     * 查看某个用户已经发布的所有的课程
     * @param userId 用户id
     * @return
     */
    @Override
    public Result<Page<CourseDO>> getAllUserCourseList(Long userId) {
        List<Long> courseIdList = userCourseService.selectCourseIdListByUserId(userId);
        List<CourseDO> courseDOList = courseService.selectListById(courseIdList, null, null);
        Integer total = courseService.getTotalById(courseIdList);
        return PageResult.getSuccessResult(courseDOList, total);
    }

    @Override
    public Result addUserCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId) {
        UserCourseDO userCourse = new UserCourseDO();
        userCourse.setUserId(userId);
        userCourse.setCourseId(courseId);
        userCourse.setPublishDate(new Date());
        int count = userCourseService.insertSelective(userCourse);
        if(count > 0) {
            logger.info("添加发布课程信息成功");
            return Result.getSuccessResult("添加收藏课程信息成功");
        } else {
            logger.info("添加发布课程信息失败");
            return Result.getBusinessException("添加收藏课程信息失败","");
        }
    }

    /**
     * 根据用户ID和课程ID删除发布的课程
     * @param query (long userId, long[] courseId)
     * @return
     */
    @Override
    public Result deleteUserCourse(@RequestBody Query query) {
        Long userId = query.getUserId();
        Long[] courseIds = query.getCourseIds();

        int count = userCourseService.deleteListById(userId, courseIds);
        if(count > 0) {
            logger.info("批量删除发布的课程信息成功");
            return Result.getSuccessResult("批量删除发布的课程信息成功");
        } else {
            logger.info("批量删除发布的课程信息失败");
            return Result.getBusinessException("批量删除发布的课程信息失败","");
        }
    }
}
