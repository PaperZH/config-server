package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.api.CollectCourseApi;
import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.common.PageResult;
import com.ucar.qtcassist.api.model.DO.CourseTypeDO;
import com.ucar.qtcassist.api.model.DO.QueryDO;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.DO.CollectCourseDO;
import com.ucar.qtcassist.api.model.DO.CourseDO;
import com.ucar.qtcassist.api.model.VO.CourseVO;
import com.ucar.qtcassist.course.service.CollectCourseService;
import com.ucar.qtcassist.course.service.CourseService;
import com.ucar.qtcassist.api.model.VO.QueryVO;
import com.ucar.qtcassist.course.service.CourseTypeService;
import com.ucar.qtcassist.course.util.QueryConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/collectCourse")
public class CollectCourseController implements CollectCourseApi {

    private static final Logger logger = LoggerFactory.getLogger(CollectCourseController.class);

    @Autowired
    private CollectCourseService collectCourseService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseTypeService courseTypeService;

    /**
     * 查询某个用户收藏的课程
     * @param  queryVO (long userId, String courseName,Date startDate, Date endDate, int currentPage, int pageSize)
     * @return
     */
    @Override
    public Result<Page<CourseVO>> getCollectCourseList(@RequestBody QueryVO queryVO) {
        QueryDO queryDO = QueryConvertUtil.convertToQueryDO(queryVO);
        List<Long> courseIdList = null;
        List<CourseDO> courseDOList = null;
        List<CourseVO> courseVOList =null;
        Integer total = 0;

        courseIdList = collectCourseService.selectCourseIdList(queryDO.getUserId());
        if(courseIdList != null && courseIdList.size() > 0) {
            //根据courseIdList, courseName, startDate, endDate等条件统计记录的总数
            total = courseService.getTotalByIdListAndCondition(courseIdList, queryDO);
            if(total == 0) {
                courseVOList = null;
            } else {
                queryDO.setCourseIdsFromList(courseIdList);
                // 根据courseIdList, courseName, startDate, endDate, startIndex, pageSize等条件查询用户收藏的课程列表
                courseDOList = courseService.getListByCondition(queryDO);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                courseVOList = new ArrayList<CourseVO>();
                if(courseDOList != null) {
                    for (CourseDO courseDO : courseDOList) {
                        CourseVO courseVO = new CourseVO();
                        courseVO.setCourseId(courseDO.getId());
                        CourseTypeDO courseType = courseTypeService.selectByPrimaryKey(courseDO.getTypeId());
                        courseVO.setCourseType(courseType);
                        courseVO.setCourseName(courseDO.getCourseName());
                        courseVO.setCourseCover(courseDO.getCourseCover());
                        courseVO.setPraiseNum(courseDO.getPraiseNum());
                        courseVO.setPublishTime(sdf.format(courseDO.getPublishTime()));
                        courseVOList.add(courseVO);
                    }
                }
            }
        } else {
            total = 0;
            courseVOList = null;
        }
        return PageResult.getSuccessResult(courseVOList, total);
    }

    /**
     * 添加收藏课程
     * @param userId 用户ID
     * @param courseId 课程ID
     * @return
     */
    @Override
    public Result addCollectCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId) {
        CollectCourseDO collectCourse = collectCourseService.getByUserIdAndCourseId(userId, courseId);
        if(collectCourse != null) {
            if(collectCourse.getDelFlag() == 1){
                return Result.getSuccessResult("已收藏该课程");
            } else {
                collectCourse.setPublishDate(new Date());
                collectCourse.setDelFlag(new Byte("1"));
                collectCourseService.updateByPrimaryKeySelective(collectCourse);
                return Result.getSuccessResult("收藏课程成功");
            }
        } else {
            collectCourse = new CollectCourseDO();
            collectCourse.setUserId(userId);
            collectCourse.setCourseId(courseId);
            collectCourse.setPublishDate(new Date());
            collectCourse.setDelFlag(new Byte("1"));
            int count = collectCourseService.insert(collectCourse);
            if (count > 0) {
                return Result.getSuccessResult("收藏课程成功");
            } else {
                return Result.getBusinessException("收藏课程失败", "-2");
            }
        }
    }

    /**
     * 根据用户ID和课程ID批量删除收藏的课程
     * @param queryVO (long userId, long[] courseId)
     * @return
     */
    @Override
    public Result deleteCollectCourseList(@RequestBody QueryVO queryVO) {
        Long userId = queryVO.getUserId();
        Long[] courseIds = queryVO.getCourseIds();

        int count = collectCourseService.deleteListByIdList(userId, courseIds);
        if(count > 0) {
            logger.info("批量删除收藏课程信息成功");
            return Result.getSuccessResult("批量删除收藏课程信息成功");
        } else {
            logger.info("批量删除收藏课程信息失败");
            return Result.getBusinessException("批量删除收藏课程信息失败","");
        }
    }
}
