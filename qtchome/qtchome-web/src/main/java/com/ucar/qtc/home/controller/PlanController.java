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
        return planService.getTeacherPlanList(params);
    }

    /**
     * 增加制定计划....包含更新操作 1
     * @param params
     * @return
     */
    @RequestMapping("/addPlan")
    public ResponseResult addPlan(@RequestBody Map<String,Object> params){
        if(params.get("id")==null){
           return planService.addPlan(params);
        }
        else{
           return planService.updatePlan(params);
        }
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
        return planService.getPublishedPlanList(params);
    }



    /**
     * 根据发布计划ID获取制定计划详细信息  1
     * @param planId
     * @return
     */
    @RequestMapping("/getPlanDetails")
    public ResponseResult getPlanDetailsByPlanId(@RequestParam long planId){
        return planService.getPlanDetailsByPlanId(planId);
    }



    /**
     * 增加发布计划。。。包含更新操作 1
     * @param params
     * @return
     */
    @RequestMapping("/addPublishedPlan")
    public ResponseResult addPublishedPlan(@RequestBody Map<String,Object> params){
        if(params.get("id")==null){
           return  planService.addPublishedPlan(params);
        }
        else{
            return planService.updatePublishedPlan(params);
        }
    }



    /**
     * 删除发布的计划  1
     * @param planId
     * @return
     */
    @GetMapping("/deletePublishedPlan")
    public ResponseResult deletePublishedPlan(@RequestParam long planId){
        return  planService.deletePublished(planId);
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
