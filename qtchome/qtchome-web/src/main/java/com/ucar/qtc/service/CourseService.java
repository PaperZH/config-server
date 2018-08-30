package com.ucar.qtc.service;

import com.ucar.qtc.utils.ResponseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient(value = "qtcassist" )
public interface CourseService {


    /**
     * 根据类型获取分页后的课程列表
     * @param params (int currentPage, int pageSize, String type)
     * @return
     */
    @RequestMapping(value = "/course/getCourseList")
    public ResponseResult getCoursesList(Map<String,Object> params);

    /**
     * 模糊查询课程列表
     * @param courseName
     * @return
     */
    @RequestMapping(value = "/course/getCourseListByName")
    public ResponseResult getCourseListByName(String courseName);

    /**
     * 根据课程ID获取课程详细信息，包括课程基本信息+教师信息+课件信息
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/course/getCourseDetail/{courseId}")
    public ResponseResult getCourseDetail(@PathVariable("courseId") Long courseId);

    /**
     * 根据课程名字或发布时间区间获取收藏的课程分页列表
     * @param params (long userId, String courseName,Date startTime, Date endTime, int currentPage, int pageSize)
     * @return
     */
    @RequestMapping(value = "/collectCourse/getCollectCourseList")
    public ResponseResult queryFavoriteCourse(Map<String,Object> params);

    /**
     * 根据用户ID和课程ID来收藏课程.
     * @param
     * @return
     */
    @RequestMapping(value = "/collectCourse/addCollectCourse/{userId}/{courseId}")
    public ResponseResult addFavoriteCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId);

    /**
     * 根据用户ID和课程ID批量删除收藏的课程
      * @param params (long userId, long[] courseId)
     * @return
     */
    @RequestMapping(value = "/collectCourse/deleteCollectCourseList")
    public ResponseResult deleteFavoriteCourse(Map<String,Object> params);


    /**
     * 根据课程名字或发布时间区间获取发布的课程分页列表
     * @param params (long userId, String courseName,Date startTime, Date endTime, int currentPage, int pageSize)
     * @return
     */
    @RequestMapping(value = "/userCourse/getUserCourseList")
    public ResponseResult queryPublishedCourse(Map<String,Object> params);

    /**
     * 根据用户ID和课程ID删除发布的课程
     * @param params (long userId, long[] courseId)
     * @return
     */
    @RequestMapping(value = "/userCourse/deleteUserCourseList")
    public ResponseResult deletePublishedCourse(Map<String,Object> params);

    /**
     * 增加课程
     * @param course (long userId , Course course)
     * @return
     */
    @RequestMapping("/course/addCourse")
    public ResponseResult addCourse(Map<String,Object> course);

    /**
     * 更新课程
     * @param course ((long userId , Course course))
     * @return
     */
    @RequestMapping("/course/updateCourse")
    public ResponseResult updateCourse(Map<String,Object> course);

    /**
     * 获取课程类型列表
     * @return
     */
    @RequestMapping(value = "/course/getCourseType")
    public ResponseResult getCourseType();

}
