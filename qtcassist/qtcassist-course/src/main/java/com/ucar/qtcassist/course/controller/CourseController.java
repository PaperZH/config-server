package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.base.model.Result;
import com.ucar.qtcassist.course.dto.Teacher;
import com.ucar.qtcassist.course.model.CollectUserCourse;
import com.ucar.qtcassist.course.model.Course;
import com.ucar.qtcassist.course.model.CreateUserCourse;
import com.ucar.qtcassist.course.model.EvaluateUserCourse;
import com.ucar.qtcassist.course.model.PraiseUserCourse;
import com.ucar.qtcassist.course.service.CollectUserCourseService;
import com.ucar.qtcassist.course.service.CourseService;
import com.ucar.qtcassist.course.service.CreateUserCourseService;
import com.ucar.qtcassist.course.service.EvaluateUserCourseService;
import com.ucar.qtcassist.course.service.PraiseUserCourseService;
import com.ucar.qtcassist.courseware.model.DO.CoursewareDO;
import com.ucar.qtcassist.courseware.service.CoursewareService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CollectUserCourseService collectUserCourseService;

    @Autowired
    private CreateUserCourseService createUserCourseService;

    @Autowired
    private EvaluateUserCourseService evaluateUserCourseService;

    @Autowired
    private PraiseUserCourseService praiseUserCourseService;

//    @Autowired
//    private UserService userService;

    @Autowired
    private CoursewareService coursewareService;

    /**
     * 查看课程详情
     * @param id  课程号
     * @return  课程详情
     */
    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable("id") String id) {

        Map<String, Object> data = new HashMap<String, Object>();
        Course course = courseService.selectByPrimaryKey(Long.valueOf(id));
        data.put("course", course);
        CreateUserCourse createUserCourse = createUserCourseService.selectByCourseId(course.getId());

        Teacher teacher = null;
        //调用远程的用户服务查询User， 参数为createUserCourse.getUserId();
//        User user = userService.get(createUserCourse.getUserId());
//        teacher.setUserId(user.getUserId());
//        teacher.setUserName(user.getName());
        data.put("teacher", teacher);

        List list = new ArrayList();
        //调用课件接口服务查询课程内容列表，参数为course.getId()
        CoursewareDO coursewareDO = coursewareService.selectByPrimaryKey(course.getId());
        list.add(coursewareDO);
        data.put("list", list);

        Result result = new Result();
        result.setCode("1000");
        result.setMsg("success");
        result.setRe(data);

        return result;
    }

    /**
     * 收藏课程
     * @param cid   课程id
     * @param uid   用户id
     */
    @GetMapping("/collect/{courseId}/{userId}")
    public Result collect(@PathVariable("courseId") String cid, @PathVariable("userId") String uid) {
        Result result = new Result();
        CollectUserCourse collectUserCourse = new CollectUserCourse();
        collectUserCourse.setCourseId(Long.valueOf(cid));
        collectUserCourse.setUserId(Long.valueOf(uid));
        collectUserCourse.setPublishDate(new Date());
        int count = collectUserCourseService.insert(collectUserCourse);
        if(count == 1) {
            result.setCode("1000");
            result.setMsg("success");
        } else {
            result.setStatus(1);
            result.setCode("1001");
            result.setMsg("failed");
        }
        return result;
    }

    @GetMapping("/list/{courseId}")
    public Result list(@PathVariable("courseId") String id) {
        Result result = new Result();
        List<Object> list = null;
        //调用课件服务接口，参数为id
        result.setCode("1000");
        result.setMsg("success");
        result.setRe(list);
        return result;
    }

    @PostMapping("/evaluate")
    public Result evaluate(EvaluateUserCourse evaluateUserCourse) {
        Result result = new Result();
        int count = evaluateUserCourseService.insert(evaluateUserCourse);
        if(count == 1) {
            result.setCode("1000");
            result.setMsg("success");
        }
        return result;
    }

    @GetMapping("/praise/{courseId}/{userId}")
    public Result praise(@PathVariable("courseId") String cid, @PathVariable("userId") String uid) {
        Result result = new Result();
        PraiseUserCourse praiseUserCourse = new PraiseUserCourse();
        praiseUserCourse.setCourseId(Long.valueOf(cid));
        praiseUserCourse.setUserId(Long.valueOf(uid));
        praiseUserCourse.setPublishDate(new Date());
        int count = praiseUserCourseService.insert(praiseUserCourse);
        if(count == 1) {
            result.setCode("1000");
            result.setMsg("success");
        }
        return result;
    }
}
