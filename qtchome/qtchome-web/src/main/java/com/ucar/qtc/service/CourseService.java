package com.ucar.qtc.service;

import com.ucar.qtc.utils.ResponseResult;

import java.util.Map;

public interface CourseService {
    /**
     * 获取要轮播的课程列表
     * @return
     */
    ResponseResult getBannerCourse();

    /**
     * 获取要推荐的课程列表
     * @return
     */
    ResponseResult getRecCourse();

    /**
     * 根据类型获取分页后的课程列表
     * @param params (int currentPage, String type)
     * @return
     */
    ResponseResult getCoursesList(Map<String,Object> params);

    /**
     * 根据课程ID获取课程详细信息，包括课程基本信息+教师信息+课件信息
     * @param courseId
     * @return
     */
    ResponseResult getCourseDetails(long courseId);

    /**
     * 获取收藏的课程列表
     * @return
     */
    ResponseResult getFavoriteCourse(long userId);

    /**
     * 根据课程名字或发布时间区间获取收藏的课程列表
     * @param params (long userId, String courseName,String startTime, String endTime)
     * @return
     */
    ResponseResult queryCourseByName(Map<String,Object> params);

    /**
     * 获取发布的课程列表
     * @return
     */
    ResponseResult getPublishedCourse(long userId);

    /**
     * 根据课程id删除课程
     * @param courseId
     * @return
     */
    ResponseResult deleteCourse(long courseId);

    /**
     * 增加课程
     * @param course
     * @return
     */
    ResponseResult addCourse(Map<String,Object> course);

    /**
     * 更新课程
     * @param course
     * @return
     */
    ResponseResult updateCourse(Map<String,Object> course);

}
