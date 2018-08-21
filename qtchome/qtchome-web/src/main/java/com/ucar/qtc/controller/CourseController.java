package com.ucar.qtc.controller;

import com.alibaba.fastjson.JSON;
import com.ucar.qtc.utils.ResponseResult;
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

    /**
     * 获取要轮播的课程，返回课程基本信息
     * @return
     */
    @RequestMapping(value = "/getBannerCourse",method = RequestMethod.GET)
    public ResponseResult getBannerCourse(){
        ArrayList<Map> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("id",Integer.toString(i));
            hashMap.put("src","static/image/1.jpg");
            list.add(hashMap);
        }
        return ResponseResult.data(list);
    }
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
     * @param query (pageSize,currentPage,type)
     * @return
     */
    @RequestMapping(value = "/getList" ,produces ="application/json")
    public ResponseResult getCoursesList(@RequestBody Map<String,Object> query){
        System.out.println(query);
        ArrayList<Map> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("courseId",Integer.toString(i));
            hashMap.put("courseCover","static/image/3.jpg");
            hashMap.put("courseName","课程名称"+i);
            hashMap.put("type_name","课程类型"+i);
            hashMap.put("courseDescription","课程描述说明");
            hashMap.put("courseScore",Integer.toString(i));
            hashMap.put("readNum",Integer.toString(10*i));
            hashMap.put("praiseNum",Integer.toString(i+10));
            hashMap.put("publishTime","2018-8-15");
            hashMap.put("updateTime","2018-8-15");
            list.add(hashMap);
        }
        return ResponseResult.data(list).put("totalDataNum","200");
    }

    /**
     * 根据课程ID来查询课程详细信息，包含课程基本信息+课件信息+Teacher信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getDetails/{id}", method = RequestMethod.GET)
    public ResponseResult getCourseDetails(@PathVariable long id){
        System.out.println(id);
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
        for (int i = 0; i <5 ; i++) {
            HashMap<String,Object> courseWare = new HashMap<>();
            courseWare.put("name","课件名称"+i);
            courseWare.put("description","课件描述"+i);
            courseWare.put("publishTime","2018-8-15");
            courseWare.put("sourceUrl","static/source.pdf");
            courseWarelist.add(courseWare);
        }
        course.put("teacher",teacher);
        course.put("courseWare",courseWarelist);
        return ResponseResult.data(course);
    }

    /**
     * 根据查询条件来获取收藏的课程
     * @return
     */
    @RequestMapping(value = "/getFavoriteCourse")
    ResponseResult getFavoriteCourse(@RequestBody Map<String,Object> params){
        System.out.println(params);
        ArrayList<Map> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("courseId",Integer.toString(i));
            hashMap.put("courseCover","static/image/4.jpg");
            hashMap.put("courseName","课程名称"+i);
            hashMap.put("type_name","课程类型"+i);
            hashMap.put("courseDescription","课程描述说明");
            hashMap.put("courseScore",Integer.toString(i));
            hashMap.put("readNum",Integer.toString(10*i));
            hashMap.put("praiseNum",Integer.toString(i+10));
            hashMap.put("publishTime","2018-8-15");
            hashMap.put("updateTime","2018-8-15");
            list.add(hashMap);
        }
        return ResponseResult.data(list);
    }

    /**
     * 根据条件批量删除收藏的课程
     * @return
     */
    @RequestMapping(value = "/deleteFavoriteCourse",produces = "application/json")
    ResponseResult deleteFavoriteCourse(@RequestBody Map<String,Object> params){
        System.out.println(params);
        System.out.println(JSON.toJSONString(params));
        System.out.println(params.get("courseId"));
        System.out.println(params.get("courseId[0]"));
//        System.out.println(list.get(0));
        return ResponseResult.ok();
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
    @RequestMapping(value = "/deleteCourse",produces = "application/json")
    ResponseResult deleteCourse(@RequestBody Map<String,Object> params){
        System.out.println(params);
        System.out.println(JSON.toJSONString(params));
        System.out.println(params.get("courseId"));
        return ResponseResult.ok();
    }

}
