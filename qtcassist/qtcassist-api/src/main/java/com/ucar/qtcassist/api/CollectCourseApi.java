package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.model.VO.CourseVO;
import com.ucar.qtcassist.api.model.VO.QueryVO;
import com.ucar.qtcassist.api.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CollectCourseApi {
    /**
     * 根据查询条件查询某个用户收藏的课程（有效课程）
     * @param queryVO (long userId, String courseName, Date startDate, Date endDate, int currentPage, int pageSize)
     * Long userId 用户id
     * String courseName 课程名称的模糊查询字符串（可以为null，表示查询所有课程）
     * Date startDate 课程发布的起始时间
     * Date endDate 课程发布的结束时间
     * Integer currentPage 分页查询的当前页
     * Integer pageSize 分布查询的每页的记录数目
     * Boolean isInValidDate 课程状态，true：有效课程（未删除未过期）， false：未删除课程（未删除）， null：所有课程
     * @return
     */
    @PostMapping("/getCollectCourseList")
    Result<Page<CourseVO>> getCollectCourseList(@RequestBody QueryVO queryVO);

    /**
     * 添加收藏课程
     * @param userId 用户ID
     * @param courseId 课程ID
     * @return
     */
    @GetMapping("/addCollectCourse/{userId}/{courseId}")
    Result addCollectCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId);

    /**
     * 根据用户ID和课程ID批量删除收藏的课程
     * @param queryVO (long userId, long[] courseIds)
     * @return
     */
    @PostMapping("/deleteCollectCourseList")
    Result deleteCollectCourseList(@RequestBody QueryVO queryVO);

    /**
     * 查看用户是否已收藏该课程
     * @param userId 用户id
     * @param courseId 课程id
     * @return
     */
    @GetMapping("/isCollectedCourse/{userId}/{courseId}")
    Result<Boolean> isCollectedCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId);
}
