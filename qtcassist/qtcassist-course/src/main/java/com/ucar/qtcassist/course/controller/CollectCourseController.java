package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.api.CollectCourseApi;
import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.common.PageResult;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.DO.CollectCourseDO;
import com.ucar.qtcassist.api.model.DO.CourseDO;
import com.ucar.qtcassist.course.service.CollectCourseService;
import com.ucar.qtcassist.course.service.CourseService;
import com.ucar.qtcassist.api.model.VO.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/collectCourse")
public class CollectCourseController implements CollectCourseApi {

    private static final Logger logger = LoggerFactory.getLogger(CollectCourseController.class);

    @Autowired
    private CollectCourseService collectCourseService;

    @Autowired
    private CourseService courseService;

    /**
     * 根据课程名字或发布时间区间获取收藏的课程分页列表
     * @param  query (long userId, String courseName,Date startTime, Date endTime, int currentPage, int pageSize)
     * @return
     */
    @Override
    public Result<Page<CourseDO>> getCollectCourseList(@RequestBody Query query) {
        Long userId = query.getUserId();
        Integer currentPage = query.getCurrentPage();
        Integer pageSize = query.getPageSize();

        Integer startIndex = (currentPage - 1) * pageSize;
        List<CourseDO> courseDOList = null;
        List<Long> courseIdList = null;
        Integer total = null;
        if(query.getCourseName() != null) {
            courseIdList = collectCourseService.selectCourseIdListByUserId(userId);
            String courseName = query.getCourseName();
            courseDOList = courseService.selectListByCourseName(courseIdList, courseName, startIndex, pageSize);
            total = courseService.getTotalByCourseName(courseIdList, courseName);
        } else {
            Date startTime = query.getStartTime();
            Date endTime = query.getEndTime();
            courseIdList = collectCourseService.selectCourseIdListByDate(userId, startTime, endTime);
            courseDOList = courseService.selectListById(courseIdList, startIndex, pageSize);
            total = courseService.getTotalById(courseIdList);
        }
        return PageResult.getSuccessResult(courseDOList, total);
    }

    /**
     * 查询收藏的所有的课程
     * @param  userId 用户Id
     * @return
     */
    @Override
    public Result<Page<CourseDO>> getAllCollectCourseList(Long userId) {
        List<Long> courseIdList = collectCourseService.selectCourseIdListByUserId(userId);
        List<CourseDO> courseDOList = courseService.selectListById(courseIdList, null, null);
        Integer total = courseService.getTotalById(courseIdList);
        return PageResult.getSuccessResult(courseDOList, total);
    }

    /**
     * 添加收藏课程
     * @param userId 用户ID
     * @param courseId 课程ID
     * @return
     */
    @Override
    public Result addCollectCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId) {
        CollectCourseDO collectCourse = new CollectCourseDO();
        collectCourse.setUserId(userId);
        collectCourse.setCourseId(courseId);
        collectCourse.setPublishDate(new Date());
        int count = collectCourseService.insertSelective(collectCourse);
        if(count > 0) {
            logger.info("添加收藏课程信息成功");
            return Result.getSuccessResult("添加收藏课程信息成功");
        } else {
            logger.info("添加收藏课程信息失败");
            return Result.getBusinessException("添加收藏课程信息失败","");
        }
    }

    /**
     * 根据用户ID和课程ID批量删除收藏的课程
     * @param //params (long userId, long[] courseId)
     * @return
     */
    @Override
    public Result deleteCollectCourse(@RequestBody Query query) {
        Long userId = query.getUserId();
        Long[] courseIds = query.getCourseIds();

        int count = collectCourseService.deleteListById(userId, courseIds);
        if(count > 0) {
            logger.info("批量删除收藏课程信息成功");
            return Result.getSuccessResult("批量删除收藏课程信息成功");
        } else {
            logger.info("批量删除收藏课程信息失败");
            return Result.getBusinessException("批量删除收藏课程信息失败","");
        }
    }
}
