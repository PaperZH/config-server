package com.ucar.qtcassist.api;

import com.alibaba.fastjson.JSONObject;
import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.model.DO.CourseDO;
import com.ucar.qtcassist.api.model.VO.QueryVO;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.VO.CourseUserVO;
import com.ucar.qtcassist.api.model.VO.CourseDetailVO;
import com.ucar.qtcassist.api.model.VO.CourseVO;
import com.ucar.qtcassist.api.model.VO.QueryVO;
import netscape.javascript.JSObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public interface CourseApi {
    /**
     * 根据类型获取分页后的课程列表
     * @param queryVO (int currentPage, int pageSize, String type)
     * @return
     */
    @PostMapping("/getCourseList")
    Result<Page<CourseVO>> getCourseList(@RequestBody QueryVO queryVO);

    /**
     * 根据课程Id来查询课程列表
     * @param params
     * @return
     */
    @PostMapping("/getCourseListByIds")
    List<CourseDO> getCourseListByIds(@RequestBody Map<String,Object> params);

    /**
     * 根据课程ID获取课程详细信息，包括课程基本信息+教师信息+课件信息
     * @param courseId
     * @return
     */
    @GetMapping("/getCourseDetail/{courseId}")
    Result<CourseDetailVO> getCourseDetail(@PathVariable("courseId") Long courseId);

    /**
     * 增加课程
     * @param courseUser (long userId , Course course)
     * @return
     */
    @PostMapping("/addCourse")
    Result addCourse(@RequestBody CourseUserVO courseUser);

    /**
     * 更新课程
     * @param courseUser (long userId , Course course)
     * @return
     */
    @PostMapping("/updateCourse")
    Result updateCourse(@RequestBody CourseUserVO courseUser);

    /**
     * 删除课程
     * @param userId 用户ID
     * @param courseId 课程ID
     * @return
     */
    @GetMapping("/deleteCourse/{userId}/{courseId}")
    Result deleteCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId);

}
