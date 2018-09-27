package com.ucar.qtc.home.controller;

import com.ucar.qtc.home.service.AdminService;
import com.ucar.qtc.home.service.CourseService;
import com.ucar.qtc.home.service.FileUploadService;
import com.ucar.qtc.home.service.SetTopService;
import com.ucar.qtc.home.utils.ResponseResult;
import com.ucar.qtcassist.api.model.VO.QueryVO;
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
 * @Description : 页面推荐内容
 * @Date : 8:22 2018/8/16
 */
@RestController
@RequestMapping("/frontPage")
public class FrontPageController {

    @Autowired
    private SetTopService setTopService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private AdminService adminService;

    /**
     * 首页轮播图
     * @return
     */
    @RequestMapping(value = "/getBanner",method = RequestMethod.GET)
    public ResponseResult getBanner(){
        return setTopService.getBanner();
    }

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
    @RequestMapping(value = "/getCourseDetail/{id}", method = RequestMethod.GET)
    public ResponseResult getCourseDetail(@PathVariable long id){
        return courseService.getCourseDetail(id);
    }

    /**
     * 图片上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseResult upload(@RequestPart("file") MultipartFile file){
        return fileUploadService.upload(file, "");
    }

    /**
     * 根据课程ID来增加课程学习次数
     * @param courseId 课程的id
     * @return
     */
    @RequestMapping(value = "/addCourseReadNum/{courseId}", method = RequestMethod.POST)
    ResponseResult addCourseReadNum(@PathVariable("courseId") Long courseId) {
        return courseService.addCourseReadNum(courseId);
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
