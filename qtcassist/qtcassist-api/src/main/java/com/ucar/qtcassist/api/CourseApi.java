package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.model.CoursewareDTO;
//import com.ucar.qtcassist.api.model.DO.BaseCoursewareDO;
import com.ucar.qtcassist.api.model.DO.CourseDO;
//import com.ucar.qtcassist.api.model.DO.CoursewareDO;
import com.ucar.qtcassist.api.model.DO.QueryDO;
import com.ucar.qtcassist.api.model.VO.QueryVO;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.VO.CourseUserVO;
import com.ucar.qtcassist.api.model.VO.CourseDetailVO;
import com.ucar.qtcassist.api.model.VO.CourseVO;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public interface CourseApi {
    /**
     * 根据类型获取分页后的课程列表
     * @param queryVO (String courseName, Integer currentPage, Integer pageSize, String type)
     * String courseName 课程名称的模糊查询字符串（可以为null，表示查询所有课程）
     * Integer currentPage 分页查询的当前页
     * Integer pageSize 分布查询的每页的记录数目
     * String type 查询的排序类型，default（默认）, time(发布时间降序), hot(点赞数量降序)
     * @return
     */
    @PostMapping("/getCourseList")
    Result<Page<CourseVO>> getCourseList(@RequestBody QueryVO queryVO);

    @PostMapping("/getRecCourseList")
    Result<List<CourseVO>> getRecCourseList(@RequestBody QueryVO queryVO);

    /**
     * 根据课程ID获取课程详细信息，包括课程基本信息+教师信息+课件信息
     * @param courseId 要查询的课程的id
     * @return
     */
    @GetMapping("/getCourseDetail/{courseId}")
    Result<CourseDetailVO<CoursewareDTO>> getCourseDetail(@PathVariable("courseId") Long courseId);

//    /**
//     * 增加课程,并使用课件库中原有的课件
//     * @param courseDetailVO
//     * @return
//     */
//    @PostMapping("/addCourseWithOldCourseware")
//    Result addCourseWithOldCourseware(@RequestBody CourseDetailVO<BaseCoursewareDO> courseDetailVO);

//    /**
//     * 增加课程,并使用新的课件
//     * @param courseDetailVO
//     * @return
//     */
//    @PostMapping("/addCourseWithNewCourseware")
//    Result addCourseWithNewCourseware(@RequestBody CourseDetailVO<CoursewareDO> courseDetailVO);

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
