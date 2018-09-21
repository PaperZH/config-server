package com.ucar.qtc.home.controller;

import com.ucar.qtc.home.dto.CoursePlanDTO;
import com.ucar.qtc.home.dto.PlanDTO;
import com.ucar.qtc.home.dto.QueryCourseDTO;
import com.ucar.qtc.home.dto.QueryPlanDTO;
import com.ucar.qtc.home.dto.UserPlanDTO;
import com.ucar.qtc.home.dto.UserPlanListDTO;
import com.ucar.qtc.home.service.PlanService;
import com.ucar.qtc.home.utils.ResponseResult;
import com.ucar.qtcassist.api.model.DO.UserPlanDO;
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
     * @param
     * @return
     */
    @RequestMapping("/getTeacherPlan")
    public ResponseResult getTeacherPlanList( QueryPlanDTO queryPlanDTO) {
        return planService.getTeacherPlanList(queryPlanDTO);
    }

    /**
     * 增加制定计划....包含更新操作 1
     * @param
     * @return
     */
    @RequestMapping("/addPlan")
    public ResponseResult addPlan(@RequestBody PlanDTO planDTO){
        System.out.println(planDTO);
        if(planDTO.getId()==null){
           return planService.addPlan(planDTO);
        }
        else{
           return planService.updatePlan(planDTO);
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
     * @param
     * @return
     */
    @GetMapping("/getPublishedPlan")
    public ResponseResult getPublishedPlan( QueryPlanDTO queryPlanDTO){
        return planService.getPublishedPlanList(queryPlanDTO);
    }

    /**
     * 分页获取学生计划列表
     *
     * @param
     * @return
     */
    @GetMapping("/getStudentPlan")
    public ResponseResult getStudentPlanList( QueryPlanDTO queryPlanDTO) {
        return planService.getPublishedPlanList(queryPlanDTO);
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
     * @param
     * @return
     */
    @RequestMapping("/addPublishedPlan")
    public ResponseResult addPublishedPlan( @RequestBody UserPlanListDTO userPlanListDTO){
           return  planService.addPublishedPlan(userPlanListDTO);
    }

    @RequestMapping("/updatePublishedPlan")
    public ResponseResult updatePublishedPlan(@RequestBody  UserPlanDO userPlanDO){
        return planService.updatePublishedPlan(userPlanDO);
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
     * @param courseDTO
     * @return
     */
    @RequestMapping("/getCourseList")
    public ResponseResult getCourseByName(QueryCourseDTO courseDTO){
        return planService.getCourseList(courseDTO);
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
     * @param
     * @return
     */
    @RequestMapping("/addPublishedCourse")
    public ResponseResult addPublishedCourse(@RequestBody  CoursePlanDTO coursePlanDTO){
        return planService.addCoursePlan(coursePlanDTO);

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
