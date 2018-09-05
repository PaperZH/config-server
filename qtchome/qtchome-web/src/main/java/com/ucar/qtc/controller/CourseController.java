package com.ucar.qtc.controller;

import com.ucar.qtc.service.CourseService;
import com.ucar.qtc.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 根据查询条件来获取发布的课程
     * @return
     */
    @RequestMapping(value = "/updateCourse", method = RequestMethod.POST)
    ResponseResult updateCourse(@RequestBody Map<String,Object> params){
        return courseService.updateCourse(params);
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
     * 根据用户ID和课程ID来点赞课程
     * @param
     * @return
     */
    @RequestMapping(value = "/deletePraiseCourse/{userId}/{courseId}")
    ResponseResult deletePraiseCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId){
        return courseService.deletePraiseCourse(userId, courseId);
    }

    /**
     * 根据查询条件来获取发布的课程
     * @return
     */
    @RequestMapping(value = "/getPublishedCourse", method = RequestMethod.GET)
    ResponseResult getPublishedCourse(@RequestParam Map<String,Object> params){
        return courseService.queryPublishedCourse(params);
    }

//    /**
//     * 添加课程
//     * @return
//     */
//    @RequestMapping(value = "/addCourseWithOldCourseware",method = RequestMethod.POST)
//    public ResponseResult addCourseWithOldCourseware(@RequestBody Map<String,Object> params){
//        System.out.println(params);
//        return courseService.addCourseWithOldCourseware(params);

//        System.out.println(JSON.toJSONString(map));
//        System.out.println(map.get("courseWare"));
//        HashMap<String,Object> course = new HashMap<>();
//        //课程基本信息
//        course.put("courseCover","static/image/2.jpg");
//        course.put("courseName","课程名称");
//        course.put("type_name","课程类型");
//        course.put("courseDescription","课程描述说明");
//        course.put("courseScore",4.5);
//        course.put("readNum",100);
//        course.put("praiseNum",100);
//        course.put("publishTime","2018-8-15");
//        course.put("updateTime","2018-8-15");
//        //课程相关教师基本信息
//        HashMap<String,Object> teacher = new HashMap<>();
//        teacher.put("username", "教师名称");
//        teacher.put("email","教师邮箱");
//        teacher.put("imageUrl","static/to.jpg");
//        //课程相关课件信息
//        ArrayList<Map> courseWarelist = new ArrayList<>();
//        for (int i = 0; i <6; i++) {
//            HashMap<String,Object> courseWare = new HashMap<>();
//            courseWare.put("name","课件名称"+i);
//            courseWare.put("description","课件描述"+i);
//            courseWare.put("publishTime","2018-8-15");
//            courseWare.put("sourceUrl","static/source.pdf");
//            courseWarelist.add(courseWare);
//        }
//        course.put("teacher",teacher);
//        course.put("courseWare",courseWarelist);
//        return  ResponseResult.data(course);
//    }

//    /**
//     * 添加课程
//     * @return
//     */
//    @RequestMapping(value = "/addCourseWithNewCourseware",method = RequestMethod.POST)
//    public ResponseResult addCourseWithNewCourseware(@RequestBody Map<String,Object> params) {
//        System.out.println(params);
//        return courseService.addCourseWithNewCourseware(params);
//    }

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
