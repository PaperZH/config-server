package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.model.DO.CourseTypeDO;
import com.ucar.qtcassist.api.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CourseTypeApi {

    /**
     * 添加课程类型
     * @param courseType 课程类型对象
     * @return
     */
    @PostMapping("/addCourseType")
    Result addCourseType(@RequestBody CourseTypeDO courseType);

    /**
     * 根据课程类型id得到课程类型对象
     * @param courseTypeId 课程类型id
     * @return
     */
    @GetMapping("/frontPage/getCourseType/{courseTypeId}")
    Result<CourseTypeDO> getCourseType(@PathVariable("courseTypeId") Long courseTypeId);

    /**
     * 查询所有的课程类型对象
     * @return
     */
    @GetMapping("/frontPage/getCourseTypeList")
    Result<List<CourseTypeDO>> getCourseTypeList();

    /**
     * 更新课程类型
     * @param courseType 课程类型对象
     * @return
     */
    @PostMapping("/updateCourseType")
    Result updateCourseType(@RequestBody CourseTypeDO courseType);

    /**
     * 根据课程类型id删除课程类型
     * @param courseTypeId 课程类型id
     * @return
     */
    @GetMapping("/deleteCourseType/{courseTypeId}")
    Result deleteCourseType(@PathVariable("courseTypeId") Long courseTypeId);
}
