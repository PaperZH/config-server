package com.ucar.qtc.controller;

import com.ucar.qtc.service.CourseService;
import com.ucar.qtc.service.FileUploadService;
import com.ucar.qtc.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author : pingli.zheng
 * @Description : 课程服务
 * @Date : 8:22 2018/8/16
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 获取要推荐的课程列表,返回课程基本信息
     * @return
     */
    @RequestMapping(value = "/getRecCourseList" , method = RequestMethod.GET)
    public ResponseResult getRecCourseList(@RequestParam Map<String,Object> params){
        return  courseService.getRecCourseList(params);
    }

    /**
     * 根据类型获取课程列表分页，返回基本课程信息
     * @param params (pageSize,currentPage,type)
     * @return
     */
    @RequestMapping(value = "/getCourseList" ,method = RequestMethod.GET)
    public ResponseResult getCourseList(@RequestParam Map<String,Object> params){
        return  courseService.getCourseList(params);
    }

    /**
     * 根据课程ID来查询课程详细信息，包含课程基本信息+课件信息+Teacher信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getDetails/{id}", method = RequestMethod.GET)
    public ResponseResult getCourseDetails(@PathVariable long id){
        return courseService.getCourseDetail(id);
    }

    @RequestMapping(value = "/file/upload")
    public ResponseResult upload(@RequestPart("file") MultipartFile file){
        return fileUploadService.upload(file, "");
    }

    /**
     * 根据查询条件来获取发布的课程
     * @return
     */
    @RequestMapping(value = "/updateCourse", method = RequestMethod.POST)
    ResponseResult updateCourse(@RequestBody Map<String,Object> params){
        return courseService.updateCourse(params);
    }

    /**
     * 创建的课程
     * @return
     */
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    ResponseResult addCourse(@RequestBody Map<String,Object> params) {
        return courseService.addCourse(params);
    }

    /**
     * 根据查询条件来获取收藏的课程
     * @return
     */
    @RequestMapping(value = "/getCollectCourse",method = RequestMethod.GET)
    ResponseResult getCollectCourse(@RequestParam Map<String,Object> params){
        return courseService.queryCollectCourse(params);
    }

    /**
     * 根据用户ID和课程ID来收藏课程
     * @param
     * @return
     */
    @RequestMapping(value = "/addCollectCourse/{userId}/{courseId}")
    ResponseResult addCollectCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId){
        return courseService.addCollectCourse(userId, courseId);
    }

    /**
     * 根据用户ID和课程ID来判断用户是否已收藏课程
     * @param
     * @return
     */
    @RequestMapping(value = "/isCollectedCourse/{userId}/{courseId}")
    ResponseResult isCollectedCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId){
        return courseService.isCollectedCourse(userId, courseId);
    }

    /**
     * 根据条件批量删除收藏的课程
     * @return
     */
    @RequestMapping(value = "/deleteCollectCourseList")
    ResponseResult deleteCollectCourseList(@RequestBody Map<String,Object> params){
        return courseService.deleteCollectCourseList(params);
    }

    /**
     * 根据用户ID和课程ID来点赞课程
     * @param
     * @return
     */
    @RequestMapping(value = "/addPraiseCourse/{userId}/{courseId}")
    ResponseResult addPraiseCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId){
        return courseService.addPraiseCourse(userId, courseId);
    }

    /**
     * 根据用户ID和课程ID来点赞课程
     * @param
     * @return
     */
    @RequestMapping(value = "/isPraisedCourse/{userId}/{courseId}")
    ResponseResult isPraisedCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId){
        return courseService.isPraisedCourse(userId, courseId);
    }

    /**
     * 根据用户ID和课程ID来判断用户是否已点赞课程
     * @param
     * @return
     */
    @RequestMapping(value = "/deletePraiseCourse/{userId}/{courseId}")
    ResponseResult deletePraiseCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId){
        return courseService.deletePraiseCourse(userId, courseId);
    }

    /**
     * 添加课程评价
     * @return
     */
    @RequestMapping(value = "/addEvaluateCourse")
    ResponseResult addEvaluateCourse(@RequestBody Map<String,Object> params){
        return courseService.addEvaluateCourse(params);
    }

    /**
     * 根据查询条件来获取发布的课程
     * @return
     */
    @RequestMapping(value = "/getPublishedCourse", method = RequestMethod.GET)
    ResponseResult getPublishedCourse(@RequestParam Map<String,Object> params){
        return courseService.queryPublishedCourse(params);
    }

    /**
     * 根据条件批量删除发布的课程
     * @return
     */
    @RequestMapping(value = "/deletePublishedCourse")
    ResponseResult deletePublishedCourse(@RequestBody Map<String,Object> params){
        return courseService.deletePublishedCourse(params);
    }

    /**
     * 获取课程类型列表
     * @return
     */
    @RequestMapping(value = "/getCourseTypeList",method = RequestMethod.GET)
    ResponseResult getCourseTypeList(){
        return courseService.getCourseTypeList();
    }

}
