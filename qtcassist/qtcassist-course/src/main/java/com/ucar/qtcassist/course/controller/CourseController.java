package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.api.CourseApi;
import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.common.PageResult;
import com.ucar.qtcassist.api.model.*;
import com.ucar.qtcassist.api.model.DO.*;
import com.ucar.qtcassist.api.model.VO.*;
import com.ucar.qtcassist.course.service.*;
import com.ucar.qtcassist.course.util.CourseConvertUtil;
import com.ucar.qtcassist.course.util.QueryConvertUtil;
import com.ucar.qtcassist.courseware.service.CoursewareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/course")
public class CourseController implements CourseApi {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseTypeService courseTypeService;

    @Autowired
    private CourseCoursewareService courseCoursewareService;

    @Autowired
    private CoursewareService coursewareService;

    @Autowired
    private UserCourseService userCourseService;

    @Autowired
    private UserFeginClient userFeginClient;

    /**
     * 根据类型获取分页后的课程列表
     * @param queryVO (String courseName, int currentPage, int pageSize, String type)
     * String courseName 课程名称的模糊查询字符串（可以为null，表示查询所有课程）
     * Integer currentPage 分页查询的当前页
     * Integer pageSize 分布查询的每页的记录数目
     * String type 查询的排序类型，default（默认）, time(发布时间降序), hot(点赞数量降序)
     * @return
     */
    @Override
    public Result<Page<CourseVO>> getCourseList(@RequestBody QueryVO queryVO) {
        QueryDO queryDO = QueryConvertUtil.convertToQueryDO(queryVO);
        //根据courseName统计记录的总数
        Integer total = courseService.getTotalByIdListAndCondition(null, queryDO);
        if(total == 0) {
            return PageResult.getSuccessResult(null, total);
        } else {
            List<CourseDO> courseDOList = null;
            // 根据courseName, startIndex, pageSize, orderType等条件查询课程页列表
            courseDOList = courseService.getList(queryDO);

            List<CourseVO> courseVOList = new ArrayList<CourseVO>();
            for (CourseDO courseDO : courseDOList) {
                CourseTypeDO courseType = courseTypeService.selectByPrimaryKey(courseDO.getTypeId());
                CourseVO courseVO = CourseConvertUtil.convertToCourseVO(courseDO);

                courseVO.setTypeName(courseType.getTypeName());
                //查询收藏数
                courseVO.setCollectNum(10);

                courseVOList.add(courseVO);
            }
            return PageResult.getSuccessResult(courseVOList, total);
        }
    }

    @Override
    public Result<List<CourseVO>> getRecCourseList(QueryVO queryVO) {
        QueryDO queryDO = QueryConvertUtil.convertToQueryDO(queryVO);
        List<CourseVO> courseVOList = null;

        List<Long> courseIdList = courseService.selectRecCourseIdList();
        if(courseIdList != null && courseIdList.size() > 0) {
            queryDO.setCourseIdsFromList(courseIdList);
            // 根据courseIdList, courseName, startDate, endDate, startIndex, pageSize等条件查询用户收藏的课程列表
            List<CourseDO> courseDOList = courseService.getListByCondition(queryDO);
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
        return Result.getSuccessResult(courseVOList);
    }

//    @Override
//    public List<CourseDO> getCourseListByIds(@RequestBody Map<String,Object> params) {
//        Object obj = params.get("id");
//        if(obj == null) {
//            return courseService.getListByIds(null);
//        }
//        List<Integer> list= (List<Integer>) obj;
//        Long[] idForLong = new Long[list.size()];
//        System.out.println(list.toString());
//        for(int i = 0 ;i < list.size();i++ ){
//            idForLong[i] = Long.valueOf(list.get(i));
//        }
//        return courseService.getListByIds(idForLong);
//    }

    /**
     * 根据课程ID获取课程详细信息，包括课程基本信息+教师信息+课件信息
     * @param courseId 要查询的课程的id
     * @return
     */
    @Override
    public Result<CourseDetailVO<CoursewareDTO>> getCourseDetail(@PathVariable("courseId") Long courseId) {
        Map<String, Object> data = new HashMap<String, Object>();
        CourseDetailVO courseDetail = new CourseDetailVO();

        CourseDO courseDO = courseService.selectByPrimaryKey(courseId);
        CourseTypeDO courseTypeDO = courseTypeService.selectByPrimaryKey(courseDO.getTypeId());
        CourseVO courseVO = CourseConvertUtil.convertToCourseVO(courseDO);

//        courseVO.setTypeName(courseTypeDO.getTypeName());
        courseVO.setCourseType(courseTypeDO);
        courseVO.setCourseDescription(courseDO.getCourseDescription());
        //查询收藏数
        courseVO.setCollectNum(10);

        courseDetail.setCourse(courseVO);

//        UserCourseDO userCourse = userCourseService.selectByCourseId(courseId);
//        //调用远程的用户服务查询User， 参数为userCourse.getUserId();
//        Result result = userFeginClient.getUserById(userCourse.getUserId());
//        UserDO user = (UserDO) result.getRe();
//        TeacherVO teacher = new TeacherVO();
//        teacher.setUserId(user.getUserId());
//        teacher.setUserName(user.getName());
//        courseDetail.setTeacher(teacher);
        courseDetail.setTeacher(null);

        List<Long> coursewareIdList = courseCoursewareService.getCoursewareIdListByCourseId(courseId);
        if(coursewareIdList == null || coursewareIdList.size() == 0) {
            courseDetail.setCoursewares(null);
        } else {
            List<CoursewareDTO> coursewareDTOList = coursewareService.selectCoursewareList(coursewareIdList);
            courseDetail.setCoursewares(coursewareDTOList);
        }
        return Result.getSuccessResult(courseDetail);
    }

//    /**
//     * 增加课程
//     * @param courseDetailVO
//     * @return
//     */
//    @Override
//    public Result addCourseWithOldCourseware(@RequestBody CourseDetailVO<BaseCoursewareDO> courseDetailVO) {
//        System.out.println(courseDetailVO);
//        CourseVO courseVO = courseDetailVO.getCourse();
//        Long userId = courseDetailVO.getTeacher().getUserId();
//
//        Long userId = courseDetailVO.getUserId();
//        CourseDO course = courseDetailVO.getCourse();
//
//        Date date = new Date();
//        course.setPublishTime(date);
//        course.setUpdateTime(date);
//        course.setReadNum(0);
//        course.setPraiseNum(0);
//
//        int count = courseService.insertSelective(course);
//        if(count > 0) {
//            Long courseId = course.getId();
//            UserCourseDO userCourse = new UserCourseDO();
//            userCourse.setUserId(userId);
//            userCourse.setCourseId(courseId);
//            userCourse.setPublishDate(date);
//            int count2 = userCourseService.insertSelective(userCourse);
//            if(count2 >= 0) {
//                logger.info("添加课程信息成功");
//                return Result.getSuccessResult("添加课程信息成功");
//            } else {
//                logger.info("添加课程信息失败");
//                return Result.getBusinessException("添加课程信息失败","");
//            }
//        } else {
//            logger.info("添加课程信息失败");
//            return Result.getBusinessException("添加课程信息失败","");
//        }

//        return Result.getSuccessResult("添加成功");
//    }

//    /**
//     * 增加课程
//     * @param courseDetailVO
//     * @return
//     */
//    @Override
//    public Result addCourseWithNewCourseware(@RequestBody CourseDetailVO<CoursewareDO> courseDetailVO) {
//        System.out.println(courseDetailVO);
//        CourseVO courseVO = courseDetailVO.getCourse();
//        Long userId = courseDetailVO.getTeacher().getUserId();
//        return Result.getSuccessResult("添加成功");
//    }


    /**
     * 增加课程
     * @param courseUser (long userId , Course course)
     * @return
     */
    @Override
    public Result addCourse(@RequestBody CourseUserVO courseUser) {
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
            int count2 = userCourseService.insertSelective(userCourse);
            if(count2 >= 0) {
                logger.info("添加课程信息成功");
                return Result.getSuccessResult("添加课程信息成功");
            } else {
                logger.info("添加课程信息失败");
                return Result.getBusinessException("添加课程信息失败","");
            }
        } else {
            logger.info("添加课程信息失败");
            return Result.getBusinessException("添加课程信息失败","");
        }
    }

    /**
     * 更新课程
     * @param courseUser (long userId , Course course)
     * @return
     */
    @Override
    public Result updateCourse(@RequestBody CourseUserVO courseUser) {
        Long userId = courseUser.getUserId();
        CourseVO courseVO = courseUser.getCourse();
        CourseDO courseDO = CourseConvertUtil.convertToCourseDO(courseVO);

        UserCourseDO userCourse = userCourseService.selectByCourseId(courseVO.getCourseId());
        if(userId.equals(userCourse.getUserId())) {
            int count = courseService.updateByPrimaryKeySelective(courseDO);
            if(count > 0) {
                logger.info("更新课程信息成功");
                return Result.getSuccessResult("更新课程信息成功");
            } else {
                logger.info("更新课程信息失败");
                return Result.getBusinessException("更新课程信息失败","");
            }
        } else {
            logger.info("用户和课程不匹配");
            return Result.getBusinessException("用户和课程不匹配","");
        }
    }

    /**
     * 删除课程
     * @param userId 用户ID
     * @param courseId 课程ID
     * @return
     */
    @Override
    public Result deleteCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId) {
        UserCourseDO userCourse = userCourseService.selectByCourseId(courseId);
        if(userId.equals(userCourse.getUserId())) {
            int count = courseService.deleteByPrimaryKey(courseId);
            if(count > 0) {
                logger.info("删除课程信息成功");
                return Result.getSuccessResult("删除课程信息成功");
            } else {
                logger.info("删除课程信息失败");
                return Result.getBusinessException("删除课程信息失败","");
            }
        } else {
            logger.info("用户和课程不匹配");
            return Result.getBusinessException("用户和课程不匹配","");
        }
    }

}
