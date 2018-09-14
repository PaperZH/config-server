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
     * 查询某个用户收藏的课程
     * @param  queryVO (long userId, String courseName,Date startDate, Date endDate, int currentPage, int pageSize，Boolean isInvalidDate)
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
