package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.api.CollectCourseApi;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.ResponseResult;
import com.ucar.qtcassist.course.model.CollectCourseDO;
import com.ucar.qtcassist.api.model.CourseDO;
import com.ucar.qtcassist.course.service.CollectCourseService;
import com.ucar.qtcassist.course.service.CourseService;
import com.ucar.qtcassist.api.model.Query;
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
    public Result add(@RequestBody CollectCourseDO collectCourse) {
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
    public Result<CollectCourseDO> get(@PathVariable("id") Long id) {
        CollectCourseDO collectCourse = collectCourseService.selectByPrimaryKey(id);
        return Result.getSuccessResult(collectCourse);
    }

    /**
     * 更新收藏课程记录
     * @param collectCourse 收藏课程记录对象
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody CollectCourseDO collectCourse) {
        int count = collectCourseService.updateByPrimaryKeySelective(collectCourse);
        if(count != 0) {
            return Result.getSuccessResult("更新收藏课程成功");
        } else {
            return Result.getBusinessException("更新收藏课程失败", "-2");
        }
    }

    /**
     * 根据课程名字或发布时间区间获取收藏的课程分页列表
     * @param  query (long userId, String courseName,Date startTime, Date endTime, int currentPage, int pageSize)
     * @return
     */
    @Override
    public ResponseResult getCollectCourse(@RequestBody Query query) {
        Long userId = query.getUserId();
        Integer currentPage = query.getCurrentPage();
        Integer pageSize = query.getPageSize();

        Integer startIndex = (currentPage - 1) * pageSize;
        List<CourseDO> courseDOList = null;
        List<Long> courseIdList = null;
        if(query.getCourseName() != null) {
            courseIdList = collectCourseService.selectCourseIdListByUserId(userId);
            String courseName = query.getCourseName();
            courseDOList = courseService.selectListByCourseName(courseIdList, courseName, startIndex, pageSize);
        } else {
            Date startTime = query.getStartTime();
            Date endTime = query.getEndTime();
            courseIdList = collectCourseService.selectCourseIdListByDate(userId, startTime, endTime);
            courseDOList = courseService.selectListById(courseIdList, startIndex, pageSize);
        }
        return ResponseResult.data(courseDOList);
    }

    /**
     * 添加收藏课程
     * @param userId 用户ID
     * @param courseId 课程ID
     * @return
     */
    @Override
    public ResponseResult addCollectCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId) {
        CollectCourseDO collectCourse = new CollectCourseDO();
        collectCourse.setUserId(userId);
        collectCourse.setCourseId(courseId);
        collectCourse.setPublishDate(new Date());
        int count = collectCourseService.insertSelective(collectCourse);
        if(count > 0) {
            logger.info("添加收藏课程信息成功");
            return ResponseResult.ok("添加收藏课程信息成功");
        } else {
            logger.info("添加收藏课程信息失败");
            return ResponseResult.error("添加收藏课程信息失败");
        }
    }

    /**
     * 根据用户ID和课程ID批量删除收藏的课程
     * @param //params (long userId, long[] courseId)
     * @return
     */
    @Override
    public ResponseResult deleteCollectCourse(@RequestBody Query query) {
        Long userId = query.getUserId();
        Long[] courseIds = query.getCourseIds();

        int count = collectCourseService.deleteListById(userId, courseIds);
        if(count > 0) {
            logger.info("批量删除收藏课程信息成功");
            return ResponseResult.ok("批量删除收藏课程信息成功");
        } else {
            logger.info("批量删除收藏课程信息失败");
            return ResponseResult.error("批量删除收藏课程信息失败");
        }
    }
}
