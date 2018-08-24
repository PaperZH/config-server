package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.model.Query;
import com.ucar.qtcassist.api.model.ResponseResult;
import com.ucar.qtcassist.api.model.UserCourseVO;
import org.springframework.web.bind.annotation.*;

public interface CourseApi {

    @PostMapping("/getCourseList")
    ResponseResult getCourseList(@RequestBody Query query);

    @GetMapping("/getCourseDetail/{courseId}")
    ResponseResult getCourseDetail(@PathVariable("courseId") Long courseId);

    @PostMapping("/addCourse")
    ResponseResult addCourse(@RequestBody UserCourseVO userCourseVO);

    @PostMapping("/updateCourse")
    ResponseResult updateCourse(@RequestBody UserCourseVO userCourseVO);

    @GetMapping("/deleteCourse/{userId}/{courseId}")
    ResponseResult deleteCourse(@PathVariable("userId") Long userId, @PathVariable Long courseId);

}
