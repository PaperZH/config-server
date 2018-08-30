package com.ucar.qtc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ucar.qtc.service.CourseService;
import com.ucar.qtc.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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
    @RequestMapping(value = "/getRecCourse" , method = RequestMethod.GET)
    public ResponseResult getRecCourses(){
        ArrayList<Map> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("courseId",Integer.toString(i));
            hashMap.put("courseCover","static/image/2.jpg");
            hashMap.put("courseName","课程名称"+i);
            hashMap.put("type_name","课程类型"+i);
            hashMap.put("courseDescription","课程描述说明");
            hashMap.put("courseScore",Integer.toString(i));
            hashMap.put("readNum",Integer.toString(10*i));
            hashMap.put("praiseNum",Integer.toString(i+10));
            hashMap.put("publishTime","2018-8-15");
            hashMap.put("updateTime","2018-8-15");
            hashMap.put("totalDataNum",100);
            list.add(hashMap);
        }
        return ResponseResult.data(list);
    }

    /**
     * 根据类型获取课程列表分页，返回基本课程信息
     * @param params (pageSize,currentPage,type)
     * @return
     */
    @RequestMapping(value = "/getList" ,method = RequestMethod.GET)
    public ResponseResult getCoursesList(@RequestParam Map<String,Object> params){
        System.out.println(params);
        return  courseService.getCoursesList(params);
    }

    /**
     * 模糊查询课程列表
     * @param courseName
     * @return
     */
    @RequestMapping("/getCourseListByName")
    public ResponseResult getCourseListByName(@RequestParam  String courseName){
        System.out.println(courseName);
        ArrayList<Map> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("courseName","课程名称"+10*i);
            hashMap.put("courseId",20*i);
            hashMap.put("courseDescription","课程描述"+10*i);
            list.add(hashMap);
        }
        return ResponseResult.data(list);
    }


    /**
     * 根据课程ID来查询课程详细信息，包含课程基本信息+课件信息+Teacher信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getDetails/{id}", method = RequestMethod.GET)
    public ResponseResult getCourseDetails(@PathVariable long id){
//        System.out.println(id);
//        HashMap<String,Object> course = new HashMap<>();
//        //课程基本信息
//        course.put("courseId",100);
//        course.put("courseCover","static/image/2.jpg");
//        course.put("courseName","课程名称");
//        course.put("typeId",2);
//        course.put("courseDescription","课程描述说明");
//        course.put("courseScore",4.5);
//        course.put("readNum",100);
//        course.put("praiseNum",100);
//        course.put("publishTime","2018-8-15");
//        course.put("updateTime","2018-8-15");
//        course.put("invalidDate", "2018-8-15");
//        //课程相关教师基本信息
//        HashMap<String,Object> teacher = new HashMap<>();
//        teacher.put("username", "教师名称");
//        teacher.put("email","教师邮箱");
//        teacher.put("avatar","static/to.jpg");
//        //课程相关课件信息
//        ArrayList<Map> courseWarelist = new ArrayList<>();
//        for (int i = 0; i <5 ; i++) {
//            HashMap<String,Object> courseWare = new HashMap<>();
//            courseWare.put("name","课件名称"+i);
//            courseWare.put("description","课件描述"+i);
//            courseWare.put("publishTime","2018-8-15");
//            courseWare.put("sourceUrl","static/source.pdf");
//            courseWarelist.add(courseWare);
//        }
//        course.put("teacher",teacher);
//        course.put("courseWare",courseWarelist);
//        return ResponseResult.data(course);
//        System.out.println(courseService.getCourseDetail(id));
        return courseService.getCourseDetail(id);
    }

    /**
     * 根据查询条件来获取收藏的课程
     * @return
     */
    @RequestMapping(value = "/getFavoriteCourse",method = RequestMethod.GET)
    ResponseResult getFavoriteCourse(@RequestParam Map<String,Object> params){
        System.out.println(params);
        return courseService.queryFavoriteCourse(params);
    }

    /**
     * 根据用户ID和课程ID来收藏课程
     * @param
     * @return
     */
    @RequestMapping(value = "/addFavoriteCourse/{userId}/{courseId}")
    ResponseResult addFavoriteCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId){
        return courseService.addFavoriteCourse(userId, courseId);
    }
    /**
     * 根据条件批量删除收藏的课程
     * @return
     */
    @RequestMapping(value = "/deleteFavoriteCourse")
    ResponseResult deleteFavoriteCourse(@RequestBody Map<String,Object> params){
        System.out.println(params);
        return courseService.deleteFavoriteCourse(params);
    }

    /**
     * 根据查询条件来获取发布的课程
     * @return
     */
    @RequestMapping(value = "/getPublishedCourse", method = RequestMethod.GET)
    ResponseResult getPublishedCourse(@RequestParam Map<String,Object> params){
        System.out.println(params);
        return courseService.queryPublishedCourse(params);
    }
    /**
     * 添加课程
     * @return
     */
    @RequestMapping(value = "/addCourse",method = RequestMethod.POST)
    public ResponseResult addCourse(@RequestBody Map<String,Object> map){
        System.out.println(map);
        System.out.println(JSON.toJSONString(map));
        System.out.println(map.get("courseWare"));
        HashMap<String,Object> course = new HashMap<>();
        //课程基本信息
        course.put("courseCover","static/image/2.jpg");
        course.put("courseName","课程名称");
        course.put("type_name","课程类型");
        course.put("courseDescription","课程描述说明");
        course.put("courseScore",4.5);
        course.put("readNum",100);
        course.put("praiseNum",100);
        course.put("publishTime","2018-8-15");
        course.put("updateTime","2018-8-15");
        //课程相关教师基本信息
        HashMap<String,Object> teacher = new HashMap<>();
        teacher.put("username", "教师名称");
        teacher.put("email","教师邮箱");
        teacher.put("imageUrl","static/to.jpg");
        //课程相关课件信息
        ArrayList<Map> courseWarelist = new ArrayList<>();
        for (int i = 0; i <6; i++) {
            HashMap<String,Object> courseWare = new HashMap<>();
            courseWare.put("name","课件名称"+i);
            courseWare.put("description","课件描述"+i);
            courseWare.put("publishTime","2018-8-15");
            courseWare.put("sourceUrl","static/source.pdf");
            courseWarelist.add(courseWare);
        }
        course.put("teacher",teacher);
        course.put("courseWare",courseWarelist);
        return  ResponseResult.data(course);
    }

    /**
     * 根据条件批量删除发布的课程
     * @return
     */
    @RequestMapping(value = "/deletePublishedCourse")
    ResponseResult deletePublishedCourse(@RequestBody Map<String,Object> params){
        System.out.println(params);
        return courseService.deletePublishedCourse(params);
    }

    /**
     * 获取课程类型列表
     * @return
     */
    @RequestMapping(value = "/getCourseTypeList",method = RequestMethod.GET)
    ResponseResult getCourseTypeList(){
//        System.out.println("到了");
//        ArrayList<Map> list = new ArrayList<>();
//            HashMap<String, Object> hashMap = new HashMap<>();
//            hashMap.put("typeId", 1);
//            hashMap.put("typeName", "Java");
//        HashMap<String, Object> hashMap2 = new HashMap<>();
//        hashMap2.put("typeId", 2);
//        hashMap2.put("typeName", "MySQL");
//        HashMap<String, Object> hashMap3= new HashMap<>();
//        hashMap3.put("typeId", 3);
//        hashMap3.put("typeName", "SpringCloud");
//        list.add(hashMap);
//        list.add(hashMap2);
//        list.add(hashMap3);
//        return ResponseResult.ok().put("courseType",list);
        return courseService.getCourseTypeList();
    }

}
