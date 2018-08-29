package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.model.DO.CourseDO;
import com.ucar.qtcassist.api.model.DO.UserCourseDO;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.VO.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserCourseApi {

    /**
     * 根据课程名字或发布时间区间获取发布的课程分页列表
     * @param query (long userId, String courseName,Date startDate, Date endDate, int currentPage, int pageSize)
     * @return
     */
    @PostMapping("/getUserCourseList")
    Result<Page<CourseDO>> getUserCourseList(@RequestBody Query query);

    @GetMapping("/addUserCourse/{userId}/{courseId}")
    Result addUserCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId);

    /**
     * 根据用户ID和课程ID删除发布的课程
     * @param query (long userId, long[] courseId)
     * @return
     */
    @PostMapping("/deleteUserCourseList")
    Result deleteUserCourseList(@RequestBody Query query);
}
