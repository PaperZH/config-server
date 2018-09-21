package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.model.CoursewareDTO;
import com.ucar.qtcassist.api.model.VO.QueryVO;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.VO.CourseDetailVO;
import com.ucar.qtcassist.api.model.VO.CourseVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Map;

public interface CourseApi {
    /**
     * 根据类型获取分页后的课程列表
     * @param queryVO (String courseName, Integer currentPage, Integer pageSize, String type)
     * String courseName 课程名称的模糊查询字符串（可以为null，表示查询所有课程）
     * Integer currentPage 分页查询的当前页
     * Integer pageSize 分布查询的每页的记录数目
     * String type 查询的排序类型，default（默认）, time(发布时间降序), hot(点赞数量降序)
     * Boolean isInValidDate 是否在有效期内，true：必须在有效期内， false（或null）：不要求在有效期内
     * @return
     */
    @PostMapping("/getCourseList")
    Result<Page<CourseVO>> getCourseList(@RequestBody QueryVO queryVO);

    /**
     * 获取推荐课程列表
     * @param queryVO(courseIds, courseName, currentPage, pageSize)
     * Long[] courseIds 要匹配的所有课程的id数组
     * String courseName 课程名称的模糊查询字符串（可以为null，表示查询所有的课程）
     * Integer currentPage 分页查询的当前页（可以为null，表示查询所有的）
     * Integer pageSize 分布查询的每页的记录数目（可以为null，表示查询所有的）
     * Boolean isInValidDate 是否在有效期内，true：必须在有效期内， false（或null）：不要求在有效期内
     * @return
     */
    @PostMapping("/getRecCourseList")
    Result<List<CourseVO>> getRecCourseList(@RequestBody QueryVO queryVO);

    /**
     * 获取所有在有效期内的课程的id、courseName、courseDescription
     * @param queryVO (String courseName, Integer currentPage, Integer pageSize)
     * String courseName 课程名称的模糊查询字符串（可以为null，表示查询所有课程）
     * Integer currentPage 分页查询的当前页（可以为null，表示查询所有的）
     * Integer pageSize 分布查询的每页的记录数目（可以为null，表示查询所有的）
     * @return
     */
    @PostMapping("/getCourseIdAndCourseName")
    Map<String, Object> getCourseIdAndCourseName(@RequestBody QueryVO queryVO);

    /**
     * 获取所有课程的id、status
     * @param queryVO (String courseName, Integer currentPage, Integer pageSize)
     * String courseName 课程名称的模糊查询字符串（可以为null，表示查询所有课程）
     * Integer currentPage 分页查询的当前页（可以为null，表示查询所有的）
     * Integer pageSize 分布查询的每页的记录数目（可以为null，表示查询所有的）
     * @return
     */
    @PostMapping("/getAllCourseIds")
    Map<String, Object> getAllCourseIds(@RequestBody QueryVO queryVO);

    /**
     * 根据课程ID获取课程详细信息，包括课程基本信息+教师信息+课件信息
     * @param courseId 要查询的课程的id
     * @return
     */
    @GetMapping("/getCourseDetail/{courseId}")
    Result<CourseDetailVO<CoursewareDTO>> getCourseDetail(@PathVariable("courseId") Long courseId);

    /**
     * 根据课程ID批量删除课程
     * @param queryVO (long[] courseIds)
     * @return
     */
    @PostMapping("/deleteCourseList")
    Result deleteCourseList(@RequestBody QueryVO queryVO);

    /**
     * 根据课程ID来增加课程学习次数
     * @param courseId 课程的id
     * @return
     */
    @GetMapping("/addCourseReadNum/{courseId}")
    Result addCourseReadNum(@PathVariable("courseId") Long courseId);

}
