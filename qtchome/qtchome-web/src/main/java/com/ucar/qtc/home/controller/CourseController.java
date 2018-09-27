package com.ucar.qtc.home.controller;

import com.ucar.qtc.home.service.AdminService;
import com.ucar.qtc.home.service.CourseService;
import com.ucar.qtc.home.service.FileUploadService;
import com.ucar.qtc.home.utils.ResponseResult;
import com.ucar.qtcassist.api.model.DO.EvaluateCourseDO;
import com.ucar.qtcassist.api.model.VO.CourseVO;
import com.ucar.qtcassist.api.model.VO.QueryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
public class CourseController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 首页轮播图
     * @return
     */
    @RequestMapping(value = "/frontPage/getBanner",method = RequestMethod.GET)
    public ResponseResult getBanner(){
        return adminService.getBanner();
    }

    /**
     * 获取要推荐的课程列表,返回课程基本信息
     * @return
     */
    @ApiOperation(value="获取要推荐的课程列表,返回课程基本信息", notes="get请求，不需要传参")
    @RequestMapping(value = "/frontPage/getRecCourseList" , method = RequestMethod.GET)
    public ResponseResult getRecCourseList(){
        return  adminService.getRecCourse();
    }

    /**
     * 根据类型获取课程列表分页，返回基本课程信息
     * @param queryVO (pageSize,currentPage,type)
     * @return
     */
    @ApiOperation(value="获取要课程列表,返回课程基本信息", notes="get请求，不需要传参")
    @RequestMapping(value = "/frontPage/course/getCourseList" ,method = RequestMethod.POST)
    public ResponseResult getCourseList(@RequestBody QueryVO queryVO){
        return  courseService.getCourseList(queryVO);
    }

    /**
     * 根据课程ID来查询课程详细信息，包含课程基本信息+课件信息+Teacher信息
     * @param id
     * @return
     */
    @ApiOperation(value="获取要课程基本信息", notes="get请求，传课程ID参数")
    @RequestMapping(value = "/frontPage/course/getCourseDetail/{id}", method = RequestMethod.GET)
    public ResponseResult getCourseDetail(@PathVariable long id){
        return courseService.getCourseDetail(id);
    }

    /**
     * 图片上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/frontPage/file/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult upload(@RequestPart("file") MultipartFile file){
        return fileUploadService.upload(file, "");
    }

    /**
     * 根据课程ID来增加课程学习次数
     * @param courseId 课程的id
     * @return
     */
    @RequestMapping(value = "/frontPage/course/addCourseReadNum/{courseId}", method = RequestMethod.POST)
    ResponseResult addCourseReadNum(@PathVariable("courseId") Long courseId) {
        return courseService.addCourseReadNum(courseId);
    }

    /**
     * 获取课程类型列表
     * @return
     */
    @RequestMapping(value = "/frontPage/courseType/getCourseTypeList",method = RequestMethod.GET)
    ResponseResult getCourseTypeList(){
        return courseService.getCourseTypeList();
    }

    /**
     * 根据查询条件来获取发布的课程
     * @return
     */
    @RequestMapping(value = "/course/getPublishCourseList", method = RequestMethod.POST)
    ResponseResult getPublishCourseList(@RequestBody QueryVO queryVO){
        return courseService.getPublishCourseList(queryVO);
    }

    /**
     * 根据查询条件来获取发布的课程
     * @return
     */
    @ApiOperation(value="更改课程", notes="get请求，不需要传参")
    @RequestMapping(value = "/course/updateCourse", method = RequestMethod.POST)
    public ResponseResult updateCourse(@RequestBody CourseVO courseVO){
        return courseService.updateCourse(courseVO);
    }

    /**
     * 创建的课程
     * @return
     */
    @ApiOperation(value="添加课程基本信息", notes="post请求")
    @RequestMapping(value = "/course/addCourse", method = RequestMethod.POST)
    public ResponseResult addCourse(@RequestBody CourseVO courseVO){
        return courseService.addCourse(courseVO);
    }

    /**
     * 根据条件批量删除发布的课程
     * @return
     */
    @RequestMapping(value = "/course/deleteCourseList", method = RequestMethod.POST)
    ResponseResult deleteCourseList(@RequestBody QueryVO queryVO){
        return courseService.deleteCourseList(queryVO);
    }

    /**
     * 根据查询条件来获取收藏的课程
     * @return
     */
    @RequestMapping(value = "/course/getCollectCourseList", method = RequestMethod.POST)
    public ResponseResult getCollectCourseList(@RequestBody QueryVO queryVO){
        return courseService.getCollectCourseList(queryVO);
    }

    /**
     * 根据用户ID和课程ID来收藏课程
     * @param
     * @return
     */
    @RequestMapping(value = "/course/addCollectCourse/{userId}/{courseId}", method = RequestMethod.POST)
    public ResponseResult addCollectCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId){
        return courseService.addCollectCourse(userId, courseId);
    }

    /**
     * 根据用户ID和课程ID来判断用户是否已收藏课程
     * @param
     * @return
     */
    @RequestMapping(value = "/course/isCollectedCourse/{userId}/{courseId}", method = RequestMethod.POST)
    public ResponseResult isCollectedCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId){
        return courseService.isCollectedCourse(userId, courseId);
    }

    /**
     * 根据条件批量删除收藏的课程
     * @return
     */
    @RequestMapping(value = "/course/deleteCollectCourseList", method = RequestMethod.POST)
    public ResponseResult deleteCollectCourseList(@RequestBody QueryVO queryVO){
        return courseService.deleteCollectCourseList(queryVO);
    }

    /**
     * 根据用户ID和课程ID来点赞课程
     * @param
     * @return
     */
    @RequestMapping(value = "/course/addPraiseCourse/{userId}/{courseId}" , method = RequestMethod.POST)
    ResponseResult addPraiseCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId){
        return courseService.addPraiseCourse(userId, courseId);
    }

    /**
     * 根据用户ID和课程ID来点赞课程
     * @param
     * @return
     */
    @RequestMapping(value = "/course/isPraisedCourse/{userId}/{courseId}", method = RequestMethod.POST)
    ResponseResult isPraisedCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId){
        return courseService.isPraisedCourse(userId, courseId);
    }

    /**
     * 根据用户ID和课程ID来判断用户是否已点赞课程
     * @param
     * @return
     */
    @RequestMapping(value = "/course/deletePraiseCourse/{userId}/{courseId}", method = RequestMethod.POST)
    ResponseResult deletePraiseCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId){
        return courseService.deletePraiseCourse(userId, courseId);
    }

    /**
     * 添加课程评价
     * @return
     */
    @RequestMapping(value = "/course/addEvaluateCourse" , method = RequestMethod.POST)
    ResponseResult addEvaluateCourse(@RequestBody EvaluateCourseDO evaluateCourseDO){
        return courseService.addEvaluateCourse(evaluateCourseDO);
    }

}
