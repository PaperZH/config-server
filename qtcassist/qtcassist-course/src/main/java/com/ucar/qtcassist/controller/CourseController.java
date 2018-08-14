package com.ucar.qtcassist.controller;

import com.ucar.qtcassist.dto.Teacher;
import com.ucar.qtcassist.model.*;
import com.ucar.qtcassist.service.*;
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

    /**
     * 查看课程详情
     * @param id  课程号
     * @return  课程详情
     */
    @GetMapping("/detail/{id}")
    public Map<String, Object> detail(@PathVariable("id") String id) {

        Map<String, Object> data = new HashMap<String, Object>();
        Course course = courseService.selectByPrimaryKey(Long.valueOf(id));
        data.put("course", course);
        CreateUserCourse createUserCourse = createUserCourseService.selectByCourseId(course.getId());

        Teacher teacher = null;
        //调用远程的用户服务查询User， 参数为createUserCourse.getUserId();
        data.put("teacher", teacher);

        List list = null;
        //调用课件接口服务查询课程内容列表，参数为course.getId()
        data.put("list", list);

        return data;
    }

    /**
     * 收藏课程
     * @param cid   课程id
     * @param uid   用户id
     */
    @PostMapping("/collect/{courseId}/{userId}")
    public void collect(@PathVariable("courseId") String cid, @PathVariable("userId") String uid) {
        CollectUserCourse collectUserCourse = new CollectUserCourse();
        collectUserCourse.setCourseId(Long.valueOf(cid));
        collectUserCourse.setUserId(Long.valueOf(uid));
        collectUserCourse.setPublishDate(new Date());
        int count = collectUserCourseService.insert(collectUserCourse);
        if(count == 1) {

        }

    }

    @GetMapping("/list/{courseId}")
    public void list(@PathVariable("courseId") String id) {
        List<Object> list = null;
        //调用课件服务接口，参数为id

    }

    @PostMapping("/evaluate")
    public void evaluate(EvaluateUserCourse evaluateUserCourse) {
        int count = evaluateUserCourseService.insert(evaluateUserCourse);
        if(count == 1) {

        }
    }

    @PostMapping("/praise/{courseId}/{userId}")
    public void praise(@PathVariable("courseId") String cid, @PathVariable("userId") String uid) {
        PraiseUserCourse praiseUserCourse = new PraiseUserCourse();
        praiseUserCourse.setCourseId(Long.valueOf(cid));
        praiseUserCourse.setUserId(Long.valueOf(uid));
        praiseUserCourse.setPublishDate(new Date());
        int count = praiseUserCourseService.insert(praiseUserCourse);
        if(count == 1) {

        }

    }
}
