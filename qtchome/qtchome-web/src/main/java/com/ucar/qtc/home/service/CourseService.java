package com.ucar.qtc.home.service;

import com.ucar.qtc.home.utils.ResponseResult;
import com.ucar.qtcassist.api.model.DO.EvaluateCourseDO;
import com.ucar.qtcassist.api.model.VO.CourseUserVO;
import com.ucar.qtcassist.api.model.VO.QueryVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "qtcassist")
public interface CourseService {

    /**
     * 根据类型获取分页后的课程列表
     * @param queryVO (String courseName, int currentPage, int pageSize, String type)
     * @return
     */
    @RequestMapping(value = "/course/getCourseList")
    public ResponseResult getCourseList(QueryVO queryVO);

    /**
     * 根据课程ID获取课程详细信息，包括课程基本信息+教师信息+课件信息
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/course/getCourseDetail/{courseId}")
    public ResponseResult getCourseDetail(@PathVariable("courseId") Long courseId);

    /**
     * 根据课程名字或发布时间区间获取收藏的课程分页列表
     * @param queryVO (long userId, String courseName,Date startTime, Date endTime, int currentPage, int pageSize)
     * @return
     */
    @RequestMapping(value = "/collectCourse/getCollectCourseList")
    public ResponseResult queryCollectCourse(QueryVO queryVO);

    /**
     * 根据用户ID和课程ID来收藏课程.
     * @param
     * @return
     */
    @RequestMapping(value = "/collectCourse/addCollectCourse/{userId}/{courseId}")
    public ResponseResult addCollectCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId);

    /**
     * 根据用户ID和课程ID来查看用户是否已点赞课程.
     * @param
     * @return
     */
    @RequestMapping(value = "/collectCourse/isCollectedCourse/{userId}/{courseId}")
    public ResponseResult isCollectedCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId);

    /**
     * 根据用户ID和课程ID批量删除收藏的课程
      * @param queryVO (long userId, long[] courseId)
     * @return
     */
    @RequestMapping(value = "/collectCourse/deleteCollectCourseList")
    public ResponseResult deleteCollectCourseList(QueryVO queryVO);

    /**
     * 根据用户ID和课程ID来点赞课程.
     * @param
     * @return
     */
    @RequestMapping(value = "/praiseCourse/addPraiseCourse/{userId}/{courseId}")
    public ResponseResult addPraiseCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId);

    /**
     * 根据用户ID和课程ID来查看用户是否已点赞课程.
     * @param
     * @return
     */
    @RequestMapping(value = "/praiseCourse/isPraisedCourse/{userId}/{courseId}")
    public ResponseResult isPraisedCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId);

    /**
     * 根据用户ID和课程ID来取消点赞课程.
     * @param
     * @return
     */
    @RequestMapping(value = "/praiseCourse/deletePraiseCourse/{userId}/{courseId}")
    public ResponseResult deletePraiseCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId);

    /**
     * 添加课程评价
     * @return
     */
    @RequestMapping(value = "/evaluateCourse/addEvaluateCourse")
    public ResponseResult addEvaluateCourse(EvaluateCourseDO evaluateCourseDO);

    /**
     * 根据课程名字或发布时间区间获取发布的课程分页列表
     * @param queryVO (long userId, String courseName,Date startTime, Date endTime, int currentPage, int pageSize)
     * @return
     */
    @RequestMapping(value = "/userCourse/getUserCourseList")
    public ResponseResult queryPublishedCourse(QueryVO queryVO);

    /**
     * 用户添加课程
     * @param courseUserVO ((long userId , Course course))
     * @return
     */
    @RequestMapping(value = "/userCourse/addUserCourse")
    public ResponseResult addUserCourse(CourseUserVO courseUserVO);

    /**
     * 用户更新课程
     * @param courseUserVO ((long userId , Course course))
     * @return
     */
    @RequestMapping(value = "/userCourse/updateUserCourse")
    public ResponseResult updateUserCourse(CourseUserVO courseUserVO);

    /**
     * 根据用户ID和课程ID删除发布的课程
     * @param queryVO (long userId, long[] courseId)
     * @return
     */
    @RequestMapping(value = "/userCourse/deleteUserCourseList")
    public ResponseResult deletePublishedCourse(QueryVO queryVO);


    @RequestMapping
    public ResponseResult getPlanList(String planName);

    /**
     * 根据课程ID来增加课程学习次数
     * @param courseId 课程的id
     * @return
     */
    @RequestMapping(value = "/course/addCourseReadNum/{courseId}")
    public ResponseResult addCourseReadNum(@PathVariable("courseId") Long courseId);

    /**
     * 获取课程类型列表
     * @return
     */
    @RequestMapping(value = "/courseType/getCourseTypeList")
    public ResponseResult getCourseTypeList();

}
