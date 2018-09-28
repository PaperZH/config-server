package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.model.CoursewareDTO;
import com.ucar.qtcassist.api.model.VO.AddCoursewareQueryVO;
import com.ucar.qtcassist.api.model.VO.QueryVO;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.VO.CourseDetailVO;
import com.ucar.qtcassist.api.model.VO.CourseVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public interface CourseApi {
    /**
     * 根据类型获取分页后的课程列表(有效课程)
     * @param queryVO (String courseName, Integer currentPage, Integer pageSize, String type, Boolean isInValidDate)
     * String courseName 课程名称的模糊查询字符串（可以为null，表示查询所有课程）
     * Integer currentPage 分页查询的当前页
     * Integer pageSize 分布查询的每页的记录数目
     * String type 查询的排序类型，default（默认）, time(发布时间降序), hot(点赞数量降序)
     * Boolean isInValidDate 课程状态，true：有效课程（未删除未过期）， false：未删除课程（未删除）， null：所有课程
     * @return
     */
    @PostMapping("/frontPage/course/getCourseList")
    Result<Page<CourseVO>> getCourseList(@RequestBody QueryVO queryVO);

    /**
     * 根据查询条件查询某个教师发布的课程（包括有效课程、未删除课程）
     * @param queryVO (long userId, String courseName, Date startDate, Date endDate, int currentPage, int pageSize)
     * Long userId 课程的teacherId
     * String courseName 课程名称的模糊查询字符串（可以为null，表示查询所有课程）
     * Date startDate 课程发布的起始时间
     * Date endDate 课程发布的结束时间
     * Integer currentPage 分页查询的当前页
     * Integer pageSize 分布查询的每页的记录数目
     * Boolean isInValidDate 课程状态，true：有效课程（未删除未过期）， false：未删除课程（未删除）， null：所有课程
     * @return
     */
    @PostMapping("/course/getPublishCourseList")
    Result<Page<CourseVO>> getPublishCourseList(@RequestBody QueryVO queryVO);

    /**
     * 获取推荐课程列表（包括有效课程、未删除课程、所有课程）
     * @param queryVO(courseIds, courseName, currentPage, pageSize)
     * Long[] courseIds 要匹配的所有课程的id数组
     * String courseName 课程名称的模糊查询字符串（可以为null，表示查询所有的课程）
     * Integer currentPage 分页查询的当前页（可以为null，表示查询所有的）
     * Integer pageSize 分布查询的每页的记录数目（可以为null，表示查询所有的）
     * Boolean isInValidDate 课程状态，true：有效课程（未删除未过期）， false：未删除课程（未删除）， null：所有课程
     * @return
     */
    @PostMapping("/frontPage/course/getRecCourseList")
    Result<List<CourseVO>> getRecCourseList(@RequestBody QueryVO queryVO);

    /**
     * 获取所有在有效期内的课程的id、courseName、courseDescription
     * @param queryVO (String courseName, Integer currentPage, Integer pageSize)
     * String courseName 课程名称的模糊查询字符串（可以为null，表示查询所有课程）
     * Integer currentPage 分页查询的当前页（可以为null，表示查询所有的）
     * Integer pageSize 分布查询的每页的记录数目（可以为null，表示查询所有的）
     * @return
     */
    @PostMapping("/frontPage/course/getCourseIdAndCourseName")
    Map<String, Object> getCourseIdAndCourseName(@RequestBody QueryVO queryVO);

    /**
     * 根据课程ID获取课程详细信息，包括课程基本信息+教师信息+课件信息
     * @param courseId 要查询的课程的id
     * @return
     */
    @GetMapping("/frontPage/course/getCourseDetail/{courseId}")
    Result<CourseDetailVO<CoursewareDTO>> getCourseDetail(@PathVariable("courseId") Long courseId);

    /**
     * 用户增加课程
     * @param courseVO
     * @return
     */
    @PostMapping("/course/addCourse")
    Result<CourseVO> addCourse(@RequestBody CourseVO courseVO);

    /**
     * 用户更新课程
     * @param courseVO
     * @return
     */
    @PostMapping("/course/updateCourse")
    Result<CourseVO> updateCourse(@RequestBody CourseVO courseVO);

    /**
     * 根据课程ID批量删除课程
     * @param queryVO (long[] courseIds)
     * @return
     */
    @PostMapping("/course/deleteCourseList")
    Result deleteCourseList(@RequestBody QueryVO queryVO);

    /**
     * 根据课程ID来增加课程学习次数
     * @param courseId 课程的id
     * @return
     */
    @GetMapping("/frontPage/course/addCourseReadNum/{courseId}")
    Result addCourseReadNum(@PathVariable("courseId") Long courseId);



}
