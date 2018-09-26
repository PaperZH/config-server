package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.model.VO.CourseVO;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.VO.QueryVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserCourseApi {

    /**
     * 根据课程名字或发布时间区间获取发布的课程分页列表
     * @param queryVO (long userId, String courseName,Date startDate, Date endDate, int currentPage, int pageSize)
     * @return
     */
    @PostMapping("/getUserCourseList")
    Result<Page<CourseVO>> getUserCourseList(@RequestBody QueryVO queryVO);

    /**
     * 用户增加课程
     * @param courseVO
     * @return
     */
    @PostMapping("/addUserCourse")
    Result<CourseVO> addUserCourse(@RequestBody CourseVO courseVO);

    /**
     * 用户更新课程
     * @param courseVO
     * @return
     */
    @PostMapping("/updateUserCourse")
    Result<CourseVO> updateUserCourse(@RequestBody CourseVO courseVO);

    /**
     * 根据用户ID和课程ID删除发布的课程
     * @param queryVO (long userId, long[] courseIds)
     * @return
     */
    @PostMapping("/deleteUserCourseList")
    Result deleteUserCourseList(@RequestBody QueryVO queryVO);
}
