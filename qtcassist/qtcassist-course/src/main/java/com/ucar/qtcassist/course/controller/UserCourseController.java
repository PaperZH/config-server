package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.api.UserCourseApi;
import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.common.PageResult;
import com.ucar.qtcassist.api.model.DO.CourseTypeDO;
import com.ucar.qtcassist.api.model.DO.QueryDO;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.DO.CourseDO;
import com.ucar.qtcassist.api.model.DO.UserCourseDO;
import com.ucar.qtcassist.api.model.VO.CourseUserVO;
import com.ucar.qtcassist.api.model.VO.CourseVO;
import com.ucar.qtcassist.course.service.CourseService;
import com.ucar.qtcassist.course.service.CourseTypeService;
import com.ucar.qtcassist.course.service.UserCourseService;
import com.ucar.qtcassist.api.model.VO.QueryVO;
import com.ucar.qtcassist.course.util.CourseConvertUtil;
import com.ucar.qtcassist.course.util.QueryConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
     * @param queryVO (long userId, String courseName, Date startDate, Date endDate, int currentPage, int pageSize)
     * @return
     */
    @Override
    public Result<Page<CourseVO>> getUserCourseList(@RequestBody QueryVO queryVO) {
        QueryDO queryDO = QueryConvertUtil.convertToQueryDO(queryVO);
        List<Long> courseIdList = null;
        List<CourseDO> courseDOList = null;
        List<CourseVO> courseVOList =null;
        Integer total = 0;

        courseIdList = userCourseService.selectCourseIdList(queryDO.getUserId());
        if(courseIdList != null && courseIdList.size() > 0) {
            //根据courseIdList, courseName, startDate, endDate等条件统计记录的总数
            total = courseService.getTotalByIdListAndCondition(courseIdList, queryDO);
            if(total > 0) {
                queryDO.setCourseIdsFromList(courseIdList);
                // 根据courseIdList, courseName, startDate, endDate, startIndex, pageSize等条件查询用户发布的课程列表
                courseDOList = courseService.getListByCondition(queryDO);
                courseVOList = new ArrayList<CourseVO>();
                for (CourseDO courseDO : courseDOList) {
                    CourseTypeDO courseType = courseTypeService.selectByPrimaryKey(courseDO.getTypeId());
                    CourseVO courseVO = CourseConvertUtil.convertToCourseVO(courseDO);

                    courseVO.setTypeName(courseType.getTypeName());
                    //查询收藏数
                    courseVO.setCollectNum(10);

                    courseVOList.add(courseVO);
                }
            }
        }
        return PageResult.getSuccessResult(courseVOList, total);
    }

    /**
     * 增加课程
     * @param courseUser (long userId , CourseVO courseVO)
     * @return
     */
    @Override
    public Result<CourseVO> addUserCourse(@RequestBody CourseUserVO courseUser) {
        Long userId = courseUser.getUserId();
        CourseVO courseVO = courseUser.getCourse();
        CourseDO courseDO = CourseConvertUtil.convertToCourseDO(courseVO);

        Date date = new Date();
        courseDO.setPublishTime(date);
        courseDO.setUpdateTime(date);
        courseDO.setReadNum(0);
        courseDO.setPraiseNum(0);

        int count = courseService.insertSelective(courseDO);
        if(count > 0) {
            Long courseId = courseDO.getId();
            UserCourseDO userCourse = new UserCourseDO();
            userCourse.setUserId(userId);
            userCourse.setCourseId(courseId);
            userCourse.setPublishDate(date);
            count = userCourseService.insertSelective(userCourse);
            if(count >= 0) {
                courseVO = CourseConvertUtil.convertToCourseVO(courseDO);
                return Result.getSuccessResult(courseVO);
            }
        }
        return Result.getBusinessException("添加课程失败","");
    }

    /**
     * 用户更新课程
     * @param courseUser (long userId , CourseVO courseVO)
     * @return
     */
    @Override
    public Result<CourseVO> updateUserCourse(@RequestBody CourseUserVO courseUser) {
        Long userId = courseUser.getUserId();
        CourseVO courseVO = courseUser.getCourse();
        UserCourseDO userCourseDO = userCourseService.selectByCourseId(courseVO.getCourseId());

        if(userId.equals(userCourseDO.getUserId())) {
            CourseDO courseDO = CourseConvertUtil.convertToCourseDO(courseVO);
            courseDO.setUpdateTime(new Date());
            int count = courseService.updateByPrimaryKeySelective(courseDO);
            if(count >= 0) {
                return Result.getSuccessResult(courseVO);
            }
        }
        return Result.getBusinessException("更新课程失败","");
    }

    /**
     * 根据用户ID和课程ID删除发布的课程
     * @param queryVO (long userId, long[] courseId)
     * @return
     */
    @Override
    public Result deleteUserCourseList(@RequestBody QueryVO queryVO) {
        Long userId = queryVO.getUserId();
        Long[] courseIds = queryVO.getCourseIds();

        int count = courseService.deleteListByIdList(courseIds);
        if(count > 0) {
            count = userCourseService.deleteListByIdList(userId, courseIds);
            if(count > 0) {
                return Result.getSuccessResult("批量删除发布的课程信息成功");
            }
        }
        return Result.getBusinessException("批量删除发布的课程信息失败","");
    }
}
