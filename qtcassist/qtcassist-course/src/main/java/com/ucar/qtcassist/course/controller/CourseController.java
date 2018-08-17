package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.base.model.Result;
import com.ucar.qtcassist.course.dto.Teacher;
import com.ucar.qtcassist.course.model.*;
import com.ucar.qtcassist.course.service.*;
import com.ucar.qtcassist.courseware.model.DO.CoursewareDO;
import com.ucar.qtcassist.courseware.model.DTO.FrontCoursewareDTO;
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
    private CollectUserCourseService collectUserCourseService;

    @Autowired
    private CreateUserCourseService createUserCourseService;

    @Autowired
    private EvaluateUserCourseService evaluateUserCourseService;

    @Autowired
    private PraiseUserCourseService praiseUserCourseService;

    @Autowired
    private CoursewareService coursewareService;

    @Autowired
    private UserService userService;

    /**
     * 查看课程详情
     * @param id  课程ID
     * @return  课程详情
     */
    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable("id") String id) {

        Map<String, Object> data = new HashMap<String, Object>();
        Course course = courseService.selectByPrimaryKey(Long.valueOf(id));
        data.put("course", course);
        CreateUserCourse createUserCourse = createUserCourseService.selectByCourseId(course.getId());

        //调用远程的用户服务查询User， 参数为createUserCourse.getUserId();
        UserDO user = userService.get(createUserCourse.getUserId());
//        teacher.setUserId(user.getUserId());
//        teacher.setUserName(user.getName());
        data.put("teacher", user);

        List list = new ArrayList();
        //调用课件接口服务查询课程内容列表，参数为course.getId()
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

    /**
     * 评论课程
     * @param evaluateUserCourse 课程评论
     * @return
     */
    @PostMapping("/evaluate")
    public Result evaluate(EvaluateUserCourse evaluateUserCourse) {
        Result result = new Result();
        evaluateUserCourse.setPublishDate(new Date());
        int count = evaluateUserCourseService.insert(evaluateUserCourse);
        if(count == 1) {
            result.setCode("1000");
            result.setMsg("success");
        } else {
            result.setCode("1001");
            result.setMsg("failed");
        }
        return result;
    }

    /**
     * 点赞课程
     * @param cid 课程ID
     * @param uid 用户ID
     * @return
     */
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
        } else {
            result.setCode("1001");
            result.setMsg("failed");
        }
        return result;
    }

    @PostMapping("/addCourseware")
    public Result addCourseware(FrontCoursewareDTO courseware) {
        Result result = new Result();
//        FrontCoursewareDTO courseware = new FrontCoursewareDTO();
//        courseware.setTypeId(1L);
//        courseware.setCoursewareName("java courseware");
//        courseware.setCoursewareDescription("d://java.txt");
//        courseware.setPublishTime(new Date());
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
