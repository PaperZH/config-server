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
     * @param params (int currentPage, int pageSize, String type)
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
     * 根据课程名字或发布时间区间获取收藏的课程分页列表
     * @param params (long userId, String courseName,Date startTime, Date endTime, int currentPage, int pageSize)
     * @return
     */
    ResponseResult queryFavoriteCourse(Map<String,Object> params);

    /**
     * 根据用户ID和课程ID来收藏课程.
     * @param params
     * @return
     */
    ResponseResult addFavoriteCourse(Map<String,Object> params);

    /**
     * 根据用户ID和课程ID批量删除收藏的课程
      * @param params (long userId, long[] courseId)
     * @return
     */
    ResponseResult deleteFavoriteCourse(Map<String,Object> params);


    /**
     * 根据课程名字或发布时间区间获取发布的课程分页列表
     * @param params (long userId, String courseName,Date startTime, Date endTime, int currentPage, int pageSize)
     * @return
     */
    ResponseResult queryPublishedCourse(Map<String,Object> params);

    /**
     * 根据用户ID和课程ID删除发布的课程
     * @param params (long userId, long[] courseId)
     * @return
     */
    ResponseResult deletePublishedCourse(Map<String,Object> params);

    /**
     * 增加课程
     * @param course (long userId , Course course)
     * @return
     */
    ResponseResult addCourse(Map<String,Object> course);

    /**
     * 更新课程
     * @param course ((long userId , Course course))
     * @return
     */
    ResponseResult updateCourse(Map<String,Object> course);

    /**
     * 获取课程类型列表
     * @return
     */
    ResponseResult getCourseType();

}
