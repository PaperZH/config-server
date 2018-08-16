package com.ucar.qtc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : pingli.zheng
 * @Description : 课程服务
 * @Date : 8:22 2018/8/16
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    /**
     * 获取的课程
     * @return
     */
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String getCourse(){
        return  "";
    }
    /**
     * 添加课程
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addCourse(){
        return  "";
    }
    /**
     * 更新课程
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String updateCourse(){
        return  "";
    }
    /**
     * 删除课程
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public String deleteCourse(){
        return  "";
    }
}
