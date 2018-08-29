package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.model.DO.PraiseCourseDO;
import com.ucar.qtcassist.api.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PraiseCourseApi {

    /**
     * 添加点赞课程记录
     */
    @GetMapping("/addPraiseCourse/{userId}/{courseId}")
    Result addPraiseCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId);

    /**
     * 删除点赞课程记录
     * @param userId 用户id
     * @param courseId 课程id
     * @return
     */
    @GetMapping("/deletePraiseCourse/{userId}/{courseId}")
    Result deletePraiseCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId);
}
