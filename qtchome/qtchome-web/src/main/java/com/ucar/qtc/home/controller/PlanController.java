package com.ucar.qtc.home.controller;

import com.ucar.qtc.home.service.PlanService;
import com.ucar.qtc.home.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    PlanService planService;
    /**
     * 根据教师ID获得学生信息 1
     *
     * @param teacherId
     * @return
     */
    @RequestMapping("/getStudents")
    public ResponseResult getStudents(@RequestParam long teacherId) {
        System.out.println(teacherId);
        return planService.getStudents(teacherId);
    }


    /**
     * 获取教师制定计划列表 1
     *
     * @param params
     * @return
     */
    @RequestMapping("/getTeacherPlan")
    public ResponseResult getTeacherPlanList(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        return planService.getTeacherPlanList(params);
    }

    /**
     * 增加制定计划....包含更新操作 1
     * @param params
     * @return
     */
    @RequestMapping("/addPlan")
    public ResponseResult addPlan(@RequestBody Map<String,Object> params){
        System.out.println(params);
        if(params.get("id")==null){
            System.out.println("执行增加操作");
            System.out.println(planService.addPlan(params));
        }
        else{
            System.out.println("执行更新操作");
            System.out.println(planService.updatePlan(params));
        }
        return ResponseResult.ok();
    }

    /**
     * 删除制定的计划  1
     * @param planId
     * @return
     */
    @GetMapping("/deletePlan")
    public ResponseResult deletePlan(@RequestParam long planId){
        return planService.deletePlan(planId);
    }




    /**
     * 分页获取发布的计划列表   1
     * @param params
     * @return
     */
    @GetMapping("/getPublishedPlan")
    public ResponseResult getPublishedPlan(@RequestParam Map<String,Object> params){
        System.out.println(params);
        return planService.getPublishedPlanList(params);
    }

    /**
     * 分页获取学生计划列表
     *
     * @param params
     * @return
     */
    @GetMapping("/getStudentPlan")
    public ResponseResult getStudentPlanList(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        return planService.getPublishedPlanList(params);
//        ArrayList<Map> list = new ArrayList();
//        for (int i = 0; i < 4; i++) {
//            HashMap<String, Object> hashMap = new HashMap<>();
//            hashMap.put("planId",i);
//            hashMap.put("planTitle", "计划名称" + i);
//            hashMap.put("startDate", "2018-8-20");
//            hashMap.put("endDate", "2018-8-30");
//            if(i%2==0){
//                hashMap.put("studentGetScore", i);}
//            hashMap.put("planStudentScore", 10);
//            if (i % 2 == 0) {
//                hashMap.put("isFinished", true);
//            } else {
//                hashMap.put("isFinished", false);
//            }
//
//            list.add(hashMap);
//        }
//        return ResponseResult.ok().put("data", list).put("total", 20);
    }



    /**
     * 根据发布计划ID获取制定计划详细信息  1
     * @param planId
     * @return
     */
    @RequestMapping("/getPlanDetails")
    public ResponseResult getPlanDetailsByPlanId(@RequestParam long planId){
        return planService.getPlanDetailsByPlanId(planId);
//        HashMap<String, Object> hash = new HashMap<>();
//        ArrayList<Map> list = new ArrayList();
//        for (int i = 0; i < 4; i++) {
//            HashMap<String, Object> hashMap = new HashMap<>();
//            hashMap.put("courseId", i);
//            hashMap.put("courseName", "Spring"+i);
//            hashMap.put("courseDescription", "课程描述"+i);
//            list.add(hashMap);
//        }
//        hash.put("courses",list);
//        hash.put("teacherName","张三");
//        HashMap<String, Object> plan = new HashMap<>();
//        plan.put("planContent","计划的内容是个啥");
//        plan.put("planDestination","计划的目的又是个啥");
//        hash.put("planId",100);
//        hash.put("studentSummary","学生总结");
//        hash.put("studentEvaluateContent","学生学习内容");
//        hash.put("plan",plan);
//        return ResponseResult.data(hash);
    }



    /**
     * 增加发布计划。。。包含更新操作
     * @param params
     * @return
     */
    @RequestMapping("/addPublishedPlan")
    public ResponseResult addPublishedPlan(@RequestBody Map<String,Object> params){
        System.out.println(params);
        if(params.get("id")==null){
            System.out.println("执行增加操作");
            System.out.println(planService.addPublishedPlan(params));
        }
        else{
            System.out.println("执行更新操作");
            System.out.println(planService.updatePublishedPlan(params));
        }
        return ResponseResult.ok();
    }



    /**
     * 删除发布的计划  1
     * @param planId
     * @return
     */
    @GetMapping("/deletePublishedPlan")
    public ResponseResult deletePublishedPlan(@RequestParam long planId){
        System.out.println(planId);
        System.out.println(planService.deletePublished(planId));
        return ResponseResult.ok("删除成功");
    }



    /**
     * 根据课程名称查询课程 1
     * @param courseName
     * @return
     */
    @RequestMapping("/getCourseList")
    public ResponseResult getCourseByName(@RequestParam String courseName){
        return planService.getCourseList(courseName);
    }
    /**
     * 根据发布计划的Id获取课程信息+计划中间表ID 1
     * @param planId
     * @return
     */
    @RequestMapping("/getCourseByPlanId")
    public ResponseResult getCourseByPlanId(@RequestParam long planId){
        return planService.getCourseByPlanId(planId);
//        ArrayList<Map> list = new ArrayList();
//        for (int i = 0; i < 4; i++) {
//            HashMap<String, Object> hashMap = new HashMap<>();
//            hashMap.put("id",i);
//            hashMap.put("courseId", i);
//            hashMap.put("courseName", "课程名字"+i);
//            hashMap.put("courseDescription", "课程描述"+i);
//            list.add(hashMap);
//        }
//        return ResponseResult.data(list);
    }

    /**
     * 增加发布计划课程  1
     * @param params
     * @return
     */
    @RequestMapping("/addPublishedCourse")
    public ResponseResult addPublishedCourse(@RequestBody Map<String,Object> params){
        return planService.addCoursePlan(params);

    }

    /**
     * 删除发布计划课程  1
     * @param id
     * @return
     */
    @GetMapping("/deletePlanCourse")
    public ResponseResult deletePlanCourse(@RequestParam long id){
        return planService.deleteCourse(id);
    }
}
