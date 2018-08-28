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
    @PostMapping("/addPraiseCourse")
    public Result addPraiseCourse(@RequestBody PraiseCourseDO praiseCourse);

    /**
     * 删除点赞课程记录
     * @param praiseCourseId 点赞课程记录id
     * @return
     */
    @GetMapping("/deletePraiseCourse/{praiseCourseId}")
    public Result deletePraiseCourse(@PathVariable("praiseCourseId") Long praiseCourseId);
}
