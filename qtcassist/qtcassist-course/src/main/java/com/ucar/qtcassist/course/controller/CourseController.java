package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.api.CourseApi;
import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.common.PageResult;
import com.ucar.qtcassist.api.model.*;
import com.ucar.qtcassist.api.model.DO.CourseDO;
import com.ucar.qtcassist.api.model.DO.CourseTypeDO;
import com.ucar.qtcassist.api.model.DO.UserCourseDO;
import com.ucar.qtcassist.api.model.VO.CourseDetailVO;
import com.ucar.qtcassist.api.model.VO.Query;
import com.ucar.qtcassist.api.model.VO.CourseUserVO;
import com.ucar.qtcassist.course.model.*;
import com.ucar.qtcassist.course.service.*;
import com.ucar.qtcassist.api.model.VO.CourseVO;
import com.ucar.qtcassist.api.model.VO.TeacherVO;
import com.ucar.qtcassist.courseware.model.DO.CoursewareDO;
import com.ucar.qtcassist.courseware.service.CoursewareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private CoursewareService coursewareService;

    @Autowired
    private UserCourseService userCourseService;

    @Autowired
    private UserFeginClient userFeginClient;

    /**
     * 根据类型获取分页后的课程列表
     * @param query (int currentPage, int pageSize, String type)
     * @return
     */
    @Override
    public Result<Page<CourseVO>> getCourseList(@RequestBody Query query) {
        Integer currentPage = query.getCurrentPage();
        Integer pageSize = query.getPageSize();
        String type = query.getType();

        Integer startIndex = (currentPage - 1) * pageSize;

        List<CourseDO> courseDOList = null;
        Integer total = courseService.getTotal();
        if(type.equals("default")) {
            courseDOList = courseService.getList(startIndex, pageSize);
        } else if (type.equals("time")) {
            courseDOList = courseService.getListByTime(startIndex, pageSize);
        } else if(type.equals("hot")) {
            courseDOList = courseService.getListByPraiseNum(startIndex, pageSize);
        } else {
            return PageResult.getBusinessException("","");
        }

        List<CourseVO> courseVOList = new ArrayList<CourseVO>();
        for(int i = 0; i < courseDOList.size() && i < pageSize; i++) {
            CourseDO courseDO = courseDOList.get(i);
            CourseVO courseVO = new CourseVO();
            courseVO.setCourseId(courseDO.getId());
            CourseTypeDO courseType = courseTypeService.selectByPrimaryKey(courseDO.getTypeId());
            courseVO.setTypeName(courseType.getTypeName());
            courseVO.setCourseName(courseDO.getCourseName());
            courseVO.setCourseCover(courseDO.getCourseCover());
            courseVO.setCourseDescription(courseDO.getCourseDescription());
            courseVO.setPraiseNum(courseDO.getPraiseNum());
            courseVO.setPublishTime(courseDO.getPublishTime());
            courseVOList.add(courseVO);
        }
        return PageResult.getSuccessResult(courseVOList, total);
    }

    /**
     * 根据课程ID获取课程详细信息，包括课程基本信息+教师信息+课件信息
     * @param courseId
     * @return
     */
    @Override
    public Result<CourseDetailVO> getCourseDetail(@PathVariable("courseId") Long courseId) {
        Map<String, Object> data = new HashMap<String, Object>();
        CourseDetailVO courseDetail = new CourseDetailVO();

        CourseDO course = courseService.selectByPrimaryKey(courseId);
        courseDetail.setCourse(course);

        UserCourseDO userCourse = userCourseService.selectByCourseId(courseId);
        //调用远程的用户服务查询User， 参数为userCourse.getUserId();
        Result result = userFeginClient.getUserById(userCourse.getUserId());
        UserDO user = (UserDO) result.getRe();
        TeacherVO teacher = new TeacherVO();
        teacher.setUserId(user.getUserId());
        teacher.setUserName(user.getName());
        courseDetail.setTeacher(teacher);

//        List<CoursewareDO> coursewareList = coursewareService.getAllCoursewareByCourseId(courseId);
//        调用课件接口服务查询课程内容列表，参数为course.getId()
//        courseDetail.setCoursewares(coursewareList);

        return Result.getSuccessResult(courseDetail);
    }

    /**
     * 增加课程
     * @param courseUser (long userId , Course course)
     * @return
     */
    @Override
    public Result addCourse(@RequestBody CourseUserVO courseUser) {
        Long userId = courseUser.getUserId();
        CourseDO course = courseUser.getCourse();

        Date date = new Date();
        course.setPublishTime(date);
        course.setUpdateTime(date);
        course.setReadNum(0);
        course.setPraiseNum(0);

        int count = courseService.insertSelective(course);
        if(count > 0) {
            Long courseId = course.getId();
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
        CourseDO course = courseUser.getCourse();
        course.setUpdateTime(new Date());

        UserCourseDO userCourse = userCourseService.selectByCourseId(course.getId());
        if(userId.equals(userCourse.getUserId())) {
            int count = courseService.updateByPrimaryKeySelective(course);
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
    public Result deleteCourse(@PathVariable("userId") Long userId, @PathVariable Long courseId) {
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







    /**
     * 查看课程的课件列表
     * @param id 课程ID
     * @return 课件列表
     */
    @GetMapping("/listCourseware/{courseId}")
    public Result<List<CoursewareDO>> listCourseware(@PathVariable("courseId") String id) {
        List<CoursewareDO> coursewareList = null;

        //调用课件服务接口，参数为id
//        coursewareList = coursewareService.getAllCourseware(Long.valueOf(id));

        return Result.getSuccessResult(coursewareList);
    }


    @PostMapping("/addCourseware")
    public Result addCourseware(@RequestBody CoursewareDO courseware) {
        Date date = new Date();
        courseware.setPublishTime(date);
        courseware.setUpdateTime(date);
        Long id = coursewareService.addCourseware(courseware);
        if(id != null) {
            courseware.setBaseCoursewareId(id);
            return Result.getSuccessResult(courseware);
        } else {
            logger.info("添加课件失败");
            return Result.getBusinessException("添加课件失败","");
        }
    }

}
