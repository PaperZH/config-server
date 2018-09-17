package com.ucar.qtc.home.controller;

import com.ucar.qtc.home.service.AdminService;
import com.ucar.qtc.home.service.CourseService;
import com.ucar.qtc.home.service.FileUploadService;
import com.ucar.qtc.home.utils.ResponseResult;
import com.ucar.qtcassist.api.model.DO.EvaluateCourseDO;
import com.ucar.qtcassist.api.model.VO.CourseUserVO;
import com.ucar.qtcassist.api.model.VO.QueryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private AdminService adminService;

    /**
     * 获取要推荐的课程列表,返回课程基本信息
     * @return
     */
    @ApiOperation(value="获取要推荐的课程列表,返回课程基本信息", notes="get请求，不需要传参")
    @RequestMapping(value = "/getRecCourseList" , method = RequestMethod.GET)
    public ResponseResult getRecCourseList(){
        return  adminService.getRecCourse();
    }

    /**
     * 根据类型获取课程列表分页，返回基本课程信息
     * @param queryVO (pageSize,currentPage,type)
     * @return
     */
    @ApiOperation(value="获取要课程列表,返回课程基本信息", notes="get请求，不需要传参")
    @RequestMapping(value = "/getCourseList" ,method = RequestMethod.POST)
    public ResponseResult getCourseList(@RequestBody QueryVO queryVO){
        return  courseService.getCourseList(queryVO);
    }

    /**
     * 根据课程ID来查询课程详细信息，包含课程基本信息+课件信息+Teacher信息
     * @param id
     * @return
     */
    @ApiOperation(value="获取要课程基本信息", notes="get请求，传课程ID参数")
    @RequestMapping(value = "/getDetails/{id}", method = RequestMethod.GET)
    public ResponseResult getCourseDetails(@PathVariable long id){
        return courseService.getCourseDetail(id);
    }

    /**
     * 图片上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    public ResponseResult upload(@RequestPart("file") MultipartFile file){
        return fileUploadService.upload(file, "");
    }

    /**
     * 根据查询条件来获取发布的课程
     * @return
     */
    @ApiOperation(value="更改课程", notes="get请求，不需要传参")
    @RequestMapping(value = "/updateUserCourse", method = RequestMethod.POST)
    public ResponseResult updateUserCourse(@RequestBody CourseUserVO courseUserVO){
        return courseService.updateUserCourse(courseUserVO);
    }

    /**
     * 创建的课程
     * @return
     */
    @ApiOperation(value="添加课程基本信息", notes="post请求")
    @RequestMapping(value = "/addUserCourse", method = RequestMethod.POST)
    public ResponseResult addUserCourse(@RequestBody CourseUserVO courseUserVO){
        return courseService.addUserCourse(courseUserVO);
    }

    /**
     * 根据查询条件来获取收藏的课程
     * @return
     */
    @RequestMapping(value = "/getCollectCourse", method = RequestMethod.POST)
    public ResponseResult getCollectCourse(@RequestBody QueryVO queryVO){
        return courseService.queryCollectCourse(queryVO);
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
