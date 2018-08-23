package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.ResponseResult;
import com.ucar.qtcassist.api.model.CourseDO;
import com.ucar.qtcassist.course.model.UserCourseDO;
import com.ucar.qtcassist.course.service.CourseService;
import com.ucar.qtcassist.course.service.UserCourseService;
import com.ucar.qtcassist.api.model.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/userCourse")
public class UserCourseController {

    private static final Logger logger = LoggerFactory.getLogger(UserCourseController.class);

    @Autowired
    private UserCourseService userCourseService;

    @Autowired
    private CourseService courseService;

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
    public Result add(@RequestBody UserCourseDO userCourse) {
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
    public Result<UserCourseDO> get(@PathVariable("id") Long id) {
        UserCourseDO userCourse = userCourseService.selectByPrimaryKey(id);
        return Result.getSuccessResult(userCourse);
    }

    /**
     * 更新用户课程记录
     * @param userCourse 用户课程记录对象
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody UserCourseDO userCourse) {
        int count = userCourseService.updateByPrimaryKeySelective(userCourse);
        if(count != 0) {
            return Result.getSuccessResult("更新用户课程成功");
        } else {
            return Result.getBusinessException("更新用户课程失败", "-2");
        }
    }

    /**
     * 根据课程名字或发布时间区间获取发布的课程分页列表
     * @param query (long userId, String courseName,Date startTime, Date endTime, int currentPage, int pageSize)
     * @return
     */
    @PostMapping("/getPublishedCourse")
    public ResponseResult getPublishedCourse(@RequestBody Query query) {
        Long userId = query.getUserId();
        Integer currentPage = query.getCurrentPage();
        Integer pageSize = query.getPageSize();

        Integer startIndex = (currentPage - 1) * pageSize;
        List<CourseDO> courseDOList = null;
        List<Long> courseIdList = null;
        if(query.getCourseName() != null) {
            courseIdList = userCourseService.selectCourseIdListByUserId(userId);
            String courseName = query.getCourseName();
            courseDOList = courseService.selectListByCourseName(courseIdList, courseName, startIndex, pageSize);
        } else {
            Date startTime = query.getStartTime();
            Date endTime = query.getEndTime();
            courseIdList = userCourseService.selectCourseIdListByDate(userId, startTime, endTime);
            courseDOList = courseService.selectListById(courseIdList, startIndex, pageSize);
        }
        return ResponseResult.data(courseDOList);
    }

    /**
     * 根据用户ID和课程ID删除发布的课程
     * @param query (long userId, long[] courseId)
     * @return
     */
    @PostMapping("/deletePublishedCourse")
    public ResponseResult deletePublishedCourse(@RequestBody Query query) {
        Long userId = query.getUserId();
        Long[] courseIds = query.getCourseIds();

        int count = userCourseService.deleteListById(userId, courseIds);
        if(count > 0) {
            logger.info("批量删除发布的课程信息成功");
            return ResponseResult.ok("批量删除发布的课程信息成功");
        } else {
            logger.info("批量删除发布的课程信息失败");
            return ResponseResult.error("批量删除发布的课程信息失败");
        }
    }
}
