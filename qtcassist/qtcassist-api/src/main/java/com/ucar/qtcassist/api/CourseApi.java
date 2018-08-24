package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.model.Query;
import com.ucar.qtcassist.api.model.ResponseResult;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.UserCourseVO;
import com.ucar.qtcassist.api.model.vo.CourseVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CourseApi {

    @PostMapping("/getCourseList")
    Result<Page<CourseVO>> getCourseList(@RequestBody Query query);

    @GetMapping("/getCourseDetail/{courseId}")
    ResponseResult getCourseDetail(@PathVariable("courseId") Long courseId);

    @PostMapping("/addCourse")
    ResponseResult addCourse(@RequestBody UserCourseVO userCourseVO);

    @PostMapping("/updateCourse")
    ResponseResult updateCourse(@RequestBody UserCourseVO userCourseVO);

    @GetMapping("/deleteCourse/{userId}/{courseId}")
    ResponseResult deleteCourse(@PathVariable("userId") Long userId, @PathVariable Long courseId);

}
