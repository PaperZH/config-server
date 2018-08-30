package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.api.UserCourseApi;
import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.common.PageResult;
import com.ucar.qtcassist.api.model.DO.CourseTypeDO;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.DO.CourseDO;
import com.ucar.qtcassist.api.model.DO.UserCourseDO;
import com.ucar.qtcassist.api.model.VO.CourseVO;
import com.ucar.qtcassist.course.service.CourseService;
import com.ucar.qtcassist.course.service.CourseTypeService;
import com.ucar.qtcassist.course.service.UserCourseService;
import com.ucar.qtcassist.api.model.VO.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/userCourse")
public class UserCourseController implements UserCourseApi {

    private static final Logger logger = LoggerFactory.getLogger(UserCourseController.class);

    @Autowired
    private UserCourseService userCourseService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseTypeService courseTypeService;

    /**
     * 根据课程名字或发布时间区间获取发布的课程分页列表
     * @param query (long userId, String courseName, Date startDate, Date endDate, int currentPage, int pageSize)
     * @return
     */
    @Override
    public Result<Page<CourseVO>> getUserCourseList(@RequestBody Query query) {
        Long userId = query.getUserId();
        Integer currentPage = query.getCurrentPage();
        Integer pageSize = query.getPageSize();

        Integer startIndex = (currentPage - 1) * pageSize;
        List<CourseDO> courseDOList = null;
        List<Long> courseIdList = null;
        Integer total = null;

        if(query.getCourseName() == null&& query.getStartDate() == null) {
            courseIdList = userCourseService.selectCourseIdList(userId, null, null);
            if(courseIdList.size() > 0) {
                courseDOList = courseService.selectListById(courseIdList, startIndex, pageSize);
                total = courseService.getTotalById(courseIdList);
            } else {
                courseDOList = null;
                total = 0;
            }
        } else if (query.getCourseName() != null && query.getStartDate() != null) {
            String courseName = query.getCourseName();
            Date startDate = query.getStartDate();
            Date endDate = query.getEndDate();
            courseIdList = userCourseService.selectCourseIdList(userId, startDate, endDate);
            if(courseIdList.size() > 0) {
                courseDOList = courseService.selectListByCourseName(courseIdList, courseName, startIndex, pageSize);
                total = courseService.getTotalByCourseName(courseIdList, courseName);
            } else {
                courseDOList = null;
                total = 0;
            }
        } else if(query.getCourseName() != null) {
            courseIdList = userCourseService.selectCourseIdList(userId, null, null);
            if(courseIdList.size() > 0) {
                String courseName = query.getCourseName();
                courseDOList = courseService.selectListByCourseName(courseIdList, courseName, startIndex, pageSize);
                total = courseService.getTotalByCourseName(courseIdList, courseName);
            } else {
                courseDOList = null;
                total = 0;
            }
        } else {
            Date startDate = query.getStartDate();
            Date endDate = query.getEndDate();
            courseIdList = userCourseService.selectCourseIdList(userId, startDate, endDate);
            if(courseIdList.size() > 0) {
                courseDOList = courseService.selectListById(courseIdList, startIndex, pageSize);
                total = courseService.getTotalById(courseIdList);
            } else {
                courseDOList = null;
                total = 0;
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<CourseVO> courseVOList = new ArrayList<CourseVO>();
        if(courseDOList != null) {
            for (CourseDO courseDO : courseDOList) {
                CourseVO courseVO = new CourseVO();
                courseVO.setCourseId(courseDO.getId());
                CourseTypeDO courseType = courseTypeService.selectByPrimaryKey(courseDO.getTypeId());
                courseVO.setTypeName(courseType.getTypeName());
                courseVO.setCourseName(courseDO.getCourseName());
                courseVO.setCourseCover(courseDO.getCourseCover());
                courseVO.setPraiseNum(courseDO.getPraiseNum());
                courseVO.setPublishTime(sdf.format(courseDO.getPublishTime()));
                courseVOList.add(courseVO);
            }
        }
        return PageResult.getSuccessResult(courseVOList, total);
    }

    @Override
    public Result addUserCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId) {
        UserCourseDO userCourse = new UserCourseDO();
        userCourse.setUserId(userId);
        userCourse.setCourseId(courseId);
        userCourse.setPublishDate(new Date());
        int count = userCourseService.insertSelective(userCourse);
        if(count > 0) {
            logger.info("添加发布课程信息成功");
            return Result.getSuccessResult("添加收藏课程信息成功");
        } else {
            logger.info("添加发布课程信息失败");
            return Result.getBusinessException("添加收藏课程信息失败","");
        }
    }

    /**
     * 根据用户ID和课程ID删除发布的课程
     * @param query (long userId, long[] courseId)
     * @return
     */
    @Override
    public Result deleteUserCourseList(@RequestBody Query query) {
        Long userId = query.getUserId();
        Long[] courseIds = query.getCourseIds();

        int count = userCourseService.deleteListByIdList(userId, courseIds);
        if(count > 0) {
            logger.info("批量删除发布的课程信息成功");
            return Result.getSuccessResult("批量删除发布的课程信息成功");
        } else {
            logger.info("批量删除发布的课程信息失败");
            return Result.getBusinessException("批量删除发布的课程信息失败","");
        }
    }
}
