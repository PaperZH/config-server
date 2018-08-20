package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.base.model.Result;
import com.ucar.qtcassist.course.model.*;
import com.ucar.qtcassist.course.service.*;
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
    private CollectUserCourseRelationService collectUserCourseRelationService;

    @Autowired
    private CreateUserCourseRelationService createUserCourseRelationService;

    @Autowired
    private EvaluateUserCourseRelationService evaluateUserCourseRelationService;

    @Autowired
    private PraiseUserCourseRelationService praiseUserCourseRelationService;

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
        CreateUserCourseRelationDO createUserCourse = createUserCourseRelationService.selectByCourseId(course.getId());

        //调用远程的用户服务查询User， 参数为createUserCourse.getUserId();
//        UserDO user = userService.get(createUserCourse.getUserId());
//        teacher.setUserId(user.getUserId());
//        teacher.setUserName(user.getName());
//        data.put("teacher", user);

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
        CollectUserCourseRelationDO collectUserCourse = new CollectUserCourseRelationDO();
        collectUserCourse.setCourseId(Long.valueOf(cid));
        collectUserCourse.setUserId(Long.valueOf(uid));
        collectUserCourse.setPublishDate(new Date());
        int count = collectUserCourseRelationService.insert(collectUserCourse);
        if(count != 0) {
            return Result.getSuccessResult("收藏课程成功");
        } else {
            return Result.getBusinessException("收藏课程失败", "-2");
        }
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
    public Result evaluate(EvaluateUserCourseRelationDO evaluateUserCourse) {
        Result result = new Result();
        evaluateUserCourse.setPublishDate(new Date());
        int count = evaluateUserCourseRelationService.insert(evaluateUserCourse);
        if(count != 0) {
            return Result.getSuccessResult("评论课程成功");
        } else {
            return Result.getBusinessException("评论课程失败", "-2");
        }
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
        PraiseUserCourseRelationDO praiseUserCourse = new PraiseUserCourseRelationDO();
        praiseUserCourse.setCourseId(Long.valueOf(cid));
        praiseUserCourse.setUserId(Long.valueOf(uid));
        praiseUserCourse.setPublishDate(new Date());
        int count = praiseUserCourseRelationService.insert(praiseUserCourse);
        if(count != 0) {
            return Result.getSuccessResult("点赞课程成功");
        } else {
            return Result.getBusinessException("点赞课程失败", "-2");
        }
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
