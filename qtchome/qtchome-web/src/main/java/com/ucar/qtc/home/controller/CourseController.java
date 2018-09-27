package com.ucar.qtc.home.controller;

import com.ucar.qtc.home.service.CourseService;
import com.ucar.qtc.home.utils.ResponseResult;
import com.ucar.qtcassist.api.model.DO.EvaluateCourseDO;
import com.ucar.qtcassist.api.model.VO.CourseVO;
import com.ucar.qtcassist.api.model.VO.QueryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : pingli.zheng
 * @Description : 课程服务
 * @Date : 8:22 2018/8/16
 */
@Api("课程接口")
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 根据查询条件来获取发布的课程
     * @return
     */
    @RequestMapping(value = "/getPublishCourseList", method = RequestMethod.POST)
    ResponseResult getPublishCourseList(@RequestBody QueryVO queryVO){
        return courseService.getPublishCourseList(queryVO);
    }

    /**
     * 根据查询条件来获取发布的课程
     * @return
     */
    @ApiOperation(value="更改课程", notes="get请求，不需要传参")
    @RequestMapping(value = "/updateCourse", method = RequestMethod.POST)
    public ResponseResult updateCourse(@RequestBody CourseVO courseVO){
        return courseService.updateCourse(courseVO);
    }

    /**
     * 创建的课程
     * @return
     */
    @ApiOperation(value="添加课程基本信息", notes="post请求")
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public ResponseResult addCourse(@RequestBody CourseVO courseVO){
        return courseService.addCourse(courseVO);
    }

    /**
     * 根据条件批量删除发布的课程
     * @return
     */
    @RequestMapping(value = "/deleteCourseList", method = RequestMethod.POST)
    ResponseResult deleteCourseList(@RequestBody QueryVO queryVO){
        return courseService.deleteCourseList(queryVO);
    }

    /**
     * 根据查询条件来获取收藏的课程
     * @return
     */
    @RequestMapping(value = "/getCollectCourseList", method = RequestMethod.POST)
    public ResponseResult getCollectCourseList(@RequestBody QueryVO queryVO){
        return courseService.getCollectCourseList(queryVO);
    }

    /**
     * 根据用户ID和课程ID来收藏课程
     * @param
     * @return
     */
    @RequestMapping(value = "/addCollectCourse/{userId}/{courseId}", method = RequestMethod.POST)
    public ResponseResult addCollectCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId){
        return courseService.addCollectCourse(userId, courseId);
    }

    /**
     * 根据用户ID和课程ID来判断用户是否已收藏课程
     * @param
     * @return
     */
    @RequestMapping(value = "/isCollectedCourse/{userId}/{courseId}", method = RequestMethod.POST)
    public ResponseResult isCollectedCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId){
        return courseService.isCollectedCourse(userId, courseId);
    }

    /**
     * 根据条件批量删除收藏的课程
     * @return
     */
    @RequestMapping(value = "/deleteCollectCourseList", method = RequestMethod.POST)
    public ResponseResult deleteCollectCourseList(@RequestBody QueryVO queryVO){
        return courseService.deleteCollectCourseList(queryVO);
    }

    /**
     * 根据用户ID和课程ID来点赞课程
     * @param
     * @return
     */
    @RequestMapping(value = "/addPraiseCourse/{userId}/{courseId}" , method = RequestMethod.POST)
    ResponseResult addPraiseCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId){
        return courseService.addPraiseCourse(userId, courseId);
    }

    /**
     * 根据用户ID和课程ID来点赞课程
     * @param
     * @return
     */
    @RequestMapping(value = "/isPraisedCourse/{userId}/{courseId}", method = RequestMethod.POST)
    ResponseResult isPraisedCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId){
        return courseService.isPraisedCourse(userId, courseId);
    }

    /**
     * 根据用户ID和课程ID来判断用户是否已点赞课程
     * @param
     * @return
     */
    @RequestMapping(value = "/deletePraiseCourse/{userId}/{courseId}", method = RequestMethod.POST)
    ResponseResult deletePraiseCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId){
        return courseService.deletePraiseCourse(userId, courseId);
    }

    /**
     * 添加课程评价
     * @return
     */
    @RequestMapping(value = "/addEvaluateCourse" , method = RequestMethod.POST)
    ResponseResult addEvaluateCourse(@RequestBody EvaluateCourseDO evaluateCourseDO){
        return courseService.addEvaluateCourse(evaluateCourseDO);
    }

}
