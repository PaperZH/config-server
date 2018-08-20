package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.base.model.Result;
import com.ucar.qtcassist.course.dto.Teacher;
import com.ucar.qtcassist.course.model.CourseDO;
import com.ucar.qtcassist.course.model.UserCourseDO;
import com.ucar.qtcassist.course.model.UserDO;
import com.ucar.qtcassist.course.service.CourseService;
import com.ucar.qtcassist.course.service.UserCourseService;
import com.ucar.qtcassist.course.service.UserService;
import com.ucar.qtcassist.courseware.model.DO.CoursewareDO;
import com.ucar.qtcassist.courseware.service.CoursewareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserCourseService userCourseService;

    @Autowired
    private CoursewareService coursewareService;

    @Autowired
    private UserService userService;

    /**
     * 根据课程id删除课程
     * @param id 课程Id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        int count = courseService.deleteByPrimaryKey(id);
        if(count != 0) {
            return Result.getSuccessResult("删除课程信息成功");
        } else {
            return Result.getBusinessException("删除课程信息失败", "-2");
        }
    }

    /**
     * 添加课程
     * @param course 课程对象
     * @return
     */
    @PostMapping("/add")
    public Result add(CourseDO course) {
        Date date = new Date();
        course.setPublishTime(date);
        course.setUpdateTime(date);
        int count = courseService.insertSelective(course);
        if(count != 0) {
            return Result.getSuccessResult("添加课程信息成功");
        } else {
            return Result.getBusinessException("添加课程信息失败", "-2");
        }
    }

    /**
     * 根据课程id得到课程对象
     * @param id 课程id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id) {
        CourseDO course = courseService.selectByPrimaryKey(id);
        return Result.getSuccessResult(course);
    }

    /**
     * 更新课程信息
     * @param course 课程对象
     * @return
     */
    @PostMapping("/update")
    public Result update(CourseDO course) {
        course.setUpdateTime(new Date());
        int count = courseService.updateByPrimaryKeySelective(course);
        if(count != 0) {
            return Result.getSuccessResult("更新课程信息成功");
        } else {
            return Result.getBusinessException("更新课程信息失败", "-2");
        }
    }

    /**
     * 查看课程详情
     * @param id  课程ID
     * @return  课程详情
     */
    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable("id") String id) {
        Map<String, Object> data = new HashMap<String, Object>();
        CourseDO course = courseService.selectByPrimaryKey(Long.valueOf(id));
        data.put("course", course);
        UserCourseDO userCourse = userCourseService.selectByCourseId(Long.valueOf(id));

        //调用远程的用户服务查询User， 参数为userCourse.getUserId();
        UserDO user = userService.get(userCourse.getUserId());
        Teacher teacher = new Teacher();
        teacher.setUserId(user.getUserId());
        teacher.setUserName(user.getName());
        data.put("teacher", teacher);

        List list = new ArrayList();
        //调用课件接口服务查询课程内容列表，参数为course.getId()
        data.put("list", list);

        return Result.getSuccessResult(data);
    }

    /**
     * 查看课程的课件列表
     * @param id 课程ID
     * @return 课件列表
     */
    @GetMapping("/list/{courseId}")
    public Result list(@PathVariable("courseId") String id) {
        Result result = new Result();
        List<CoursewareDO> list = null;

        //调用课件服务接口，参数为id
//        list = coursewareService.getAllCourseware(Long.valueOf(id));

        result.setCode("1000");
        result.setMsg("success");
        result.setRe(list);
        return result;
    }


    @PostMapping("/addCourseware")
    public Result addCourseware(CoursewareDO courseware) {
        Result result = new Result();
//        CoursewareDO courseware = new CoursewareDO();
//        courseware.setTypeId(1L);
//        courseware.setCoursewareName("java courseware");
//        courseware.setCoursewareDescription("d://java.txt");
        courseware.setPublishTime(new Date());
        courseware.setUpdateTime(new Date());
        Long id = coursewareService.addCourseware(courseware);
        if(id != null) {
            courseware.setBaseCoursewareId(id);
            result.setCode("1000");
            result.setMsg("success");
            result.setRe(courseware);
        } else {
            result.setCode("1001");
            result.setMsg("failed");
            result.setRe(courseware);
        }
        return result;
    }

}
