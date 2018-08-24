package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.api.CourseApi;
import com.ucar.qtcassist.api.model.CourseDO;
import com.ucar.qtcassist.api.model.ResponseResult;
import com.ucar.qtcassist.course.model.*;
import com.ucar.qtcassist.course.service.*;
import com.ucar.qtcassist.course.vo.CourseVO;
import com.ucar.qtcassist.api.model.Query;
import com.ucar.qtcassist.course.vo.Teacher;
import com.ucar.qtcassist.api.model.UserCourseVO;
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
     * 根据课程id删除课程
     * @param id 课程Id
     * @return
     */
    @GetMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        int count = courseService.deleteByPrimaryKey(id);
        if(count > 0) {
            logger.info("删除课程信息成功");
            return ResponseResult.ok("删除课程信息成功");
        } else {
            logger.info("删除课程信息失败");
            return ResponseResult.error("删除课程信息失败");
        }
    }

    /**
     * 添加课程
     * @param course 课程对象
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody CourseDO course) {
        Date date = new Date();
        course.setPublishTime(date);
        course.setUpdateTime(date);
        int count = courseService.insertSelective(course);
        if(count > 0) {
            logger.info("添加课程信息成功");
            return ResponseResult.ok("添加课程信息成功");
        } else {
            logger.info("添加课程信息失败");
            return ResponseResult.error("添加课程信息失败");
        }
    }

    /**
     * 根据课程id得到课程对象
     * @param id 课程id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResponseResult get(@PathVariable("id") Long id) {
        CourseDO course = courseService.selectByPrimaryKey(id);
        return ResponseResult.data(course);
    }

    /**
     * 更新课程信息
     * @param course 课程对象
     * @return
     */
    @PostMapping("/update")
    public ResponseResult update(@RequestBody CourseDO course) {
        course.setUpdateTime(new Date());
        int count = courseService.updateByPrimaryKeySelective(course);
        if(count > 0) {
            logger.info("更新课程信息成功");
            return ResponseResult.ok("更新课程信息成功");
        } else {
            logger.info("更新课程信息失败");
            return ResponseResult.error("更新课程信息失败");
        }
    }

    /**
     * 根据类型获取分页后的课程列表
     * @param query (int currentPage, int pageSize, String type)
     * @return
     */
    @Override
    public ResponseResult getCourseList(@RequestBody Query query) {
        Integer currentPage = query.getCurrentPage();
        Integer pageSize = query.getPageSize();
        String type = query.getType();

        Integer startIndex = (currentPage - 1) * pageSize;

        List<CourseDO> courseDOList = null;
        if(type.equals("default")) {
            courseDOList = courseService.getList(startIndex, pageSize);
        } else if (type.equals("time")) {
            courseDOList = courseService.getListByTime(startIndex, pageSize);
        } else if(type.equals("hot")) {
            courseDOList = courseService.getListByPraiseNum(startIndex, pageSize);
        } else {
            return ResponseResult.error("查询条件有问题");
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
        return ResponseResult.data(courseVOList);
    }

    /**
     * 根据课程ID获取课程详细信息，包括课程基本信息+教师信息+课件信息
     * @param courseId
     * @return
     */
    @Override
    public ResponseResult getCourseDetail(@PathVariable("courseId") Long courseId) {
        Map<String, Object> data = new HashMap<String, Object>();

        CourseDO course = courseService.selectByPrimaryKey(courseId);
        data.put("course", course);

        UserCourseDO userCourse = userCourseService.selectByCourseId(courseId);
        //调用远程的用户服务查询User， 参数为userCourse.getUserId();
        ResponseResult result = userFeginClient.getUserById(userCourse.getUserId());
        UserDO user = (UserDO) result.get("data");
        Teacher teacher = new Teacher();
        teacher.setUserId(user.getUserId());
        teacher.setUserName(user.getName());
        data.put("teacher", teacher);

//        List<CoursewareDO> coursewareList = coursewareService.getAllCoursewareByCourseId(courseId);
//        调用课件接口服务查询课程内容列表，参数为course.getId()
//        data.put("list", coursewareList);

        return ResponseResult.data(data);
    }

    /**
     * 增加课程
     * @param userCourseVO (long userId , Course course)
     * @return
     */
    @Override
    public ResponseResult addCourse(@RequestBody UserCourseVO userCourseVO) {
        Long userId = userCourseVO.getUserId();
        CourseDO course = userCourseVO.getCourse();

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
                return ResponseResult.ok("添加课程信息成功");
            } else {
                logger.info("添加课程信息失败");
                return ResponseResult.error("添加课程信息失败");
            }
        } else {
            logger.info("添加课程信息失败");
            return ResponseResult.error("添加课程信息失败");
        }
    }

    /**
     * 更新课程
     * @param userCourseVO ((long userId , Course course))
     * @return
     */
    @Override
    public ResponseResult updateCourse(@RequestBody UserCourseVO userCourseVO) {
        Long userId = userCourseVO.getUserId();
        CourseDO course = userCourseVO.getCourse();
        course.setUpdateTime(new Date());

        UserCourseDO userCourse = userCourseService.selectByCourseId(course.getId());
        if(userId.equals(userCourse.getUserId())) {
            int count = courseService.updateByPrimaryKeySelective(course);
            if(count > 0) {
                logger.info("更新课程信息成功");
                return ResponseResult.ok("更新课程信息成功");
            } else {
                logger.info("更新课程信息失败");
                return ResponseResult.error("更新课程信息失败");
            }
        } else {
            logger.info("用户和课程不匹配");
            return ResponseResult.error("用户和课程不匹配");
        }
    }

    /**
     * 删除课程
     * @param userId 用户ID
     * @param courseId 课程ID
     * @return
     */
    @Override
    public ResponseResult deleteCourse(@PathVariable("userId") Long userId, @PathVariable Long courseId) {
        UserCourseDO userCourse = userCourseService.selectByCourseId(courseId);
        if(userId.equals(userCourse.getUserId())) {
            int count = courseService.deleteByPrimaryKey(courseId);
            if(count > 0) {
                logger.info("删除课程信息成功");
                return ResponseResult.ok("删除课程信息成功");
            } else {
                logger.info("删除课程信息失败");
                return ResponseResult.error("删除课程信息失败");
            }
        } else {
            logger.info("用户和课程不匹配");
            return ResponseResult.error("用户和课程不匹配");
        }
    }







    /**
     * 查看课程的课件列表
     * @param id 课程ID
     * @return 课件列表
     */
    @GetMapping("/listCourseware/{courseId}")
    public ResponseResult listCourseware(@PathVariable("courseId") String id) {
        List<CoursewareDO> coursewareList = null;

        //调用课件服务接口，参数为id
//        coursewareList = coursewareService.getAllCourseware(Long.valueOf(id));

        return ResponseResult.data(coursewareList);
    }


    @PostMapping("/addCourseware")
    public ResponseResult addCourseware(@RequestBody CoursewareDO courseware) {
//        CoursewareDO courseware = new CoursewareDO();
//        courseware.setTypeId(1L);
//        courseware.setCoursewareName("java courseware");
//        courseware.setCoursewareDescription("d://java.txt");
        Date date = new Date();
        courseware.setPublishTime(date);
        courseware.setUpdateTime(date);
        Long id = coursewareService.addCourseware(courseware);
        if(id != null) {
            courseware.setBaseCoursewareId(id);
            return ResponseResult.data(courseware);
        } else {
            logger.info("添加课件失败");
            return ResponseResult.error("添加课件失败");
        }
    }

}
