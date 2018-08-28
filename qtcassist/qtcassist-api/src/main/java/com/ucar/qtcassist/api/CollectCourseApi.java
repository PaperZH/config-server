package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.model.DO.CourseDO;
import com.ucar.qtcassist.api.model.VO.Query;
import com.ucar.qtcassist.api.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CollectCourseApi {
    /**
     * 根据课程名字或发布时间区间获取收藏的课程分页列表
     * @param  query (long userId, String courseName,Date startTime, Date endTime, int currentPage, int pageSize)
     * @return
     */
    @PostMapping("/getCollectCourseList")
    Result<Page<CourseDO>> getCollectCourseList(@RequestBody Query query);

    /**
     * 查询收藏的所有的课程
     * @param  userId 用户Id
     * @return
     */
    @GetMapping("/getAllCollectCourseList/{userId}")
    Result<Page<CourseDO>> getAllCollectCourseList(@PathVariable Long userId);

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
     * @param //params (long userId, long[] courseId)
     * @return
     */
    @PostMapping("/deleteCollectCourse")
    Result deleteCollectCourse(@RequestBody Query query);
}
