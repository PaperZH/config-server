package com.ucar.qtc.home.service;

import com.ucar.qtc.home.dto.CoursePlanDTO;
import com.ucar.qtc.home.dto.PlanDTO;
import com.ucar.qtc.home.dto.QueryPlanDTO;
import com.ucar.qtc.home.dto.UserPlanListDTO;
import com.ucar.qtc.home.utils.ResponseResult;
import com.ucar.qtcassist.api.model.DO.UserPlanDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient("QTCASSIST")
public interface PlanService {
    /**
     * 获取学生信息
     * @param teacherId
     * @return
     */
    @RequestMapping("/userPlan/getStudents/{id}")
    public ResponseResult getStudents(@PathVariable("id") Long teacherId);
    /**
     * 分页获取教师制定计划列表
     * @param
     * @return
     */
    @PostMapping("/plan/getPlan")
    public ResponseResult getTeacherPlanList(QueryPlanDTO queryPlanDTO);

    /**
     * 分页获取发布的计划列表
     * @param
     * @return
     */
    @RequestMapping("/userPlan/getPlan")
    public ResponseResult getPublishedPlanList(QueryPlanDTO queryPlanDTO);

    /**
     * 增加制定计划
     * @param
     * @return
     */
    @RequestMapping("/plan/add")
    public ResponseResult addPlan (PlanDTO planDTO);
//
    /**
     * 更新制定计划
     * @param
     * @return
     */
    @RequestMapping("/plan/update")
    public ResponseResult updatePlan(PlanDTO planDTO);

    /**
     * 增加发布的计划
     * @param
     * @return
     */
    @RequestMapping("/userPlan/add")
    public ResponseResult addPublishedPlan (UserPlanListDTO userPlanListDTO);

    /**
     * 更新发布的计划
     * @param
     * @return
     */
    @RequestMapping("/userPlan/update")
    public ResponseResult updatePublishedPlan(UserPlanDO userPlanDO);

    /**
     * 删除制定的计划
     * @param
     * @return
     */
    @RequestMapping("/plan/delete/{planId}")
    public ResponseResult deletePlan(@PathVariable("planId") long planId);

    /**
     * 删除发布的计划
     * @param planId
     * @return
     */
    @RequestMapping("/userPlan/delete/{planId}")
    public ResponseResult deletePublished(@PathVariable("planId") long planId);

    /**
     * 增加计划课程
     * @param
     * @return
     */
    @RequestMapping("/coursePlan/add")
    public ResponseResult addCoursePlan(CoursePlanDTO coursePlanDTO);
    /**
     * 根据计划ID获取相应计划课程
     * @param planId
     * @return
     */
    @RequestMapping("/coursePlan/get/{planId}")
    public ResponseResult getCourseByPlanId(@PathVariable("planId") long planId);

    /**
     * 根据课程名称查询课程
     * @param courseName
     * @return
     */
    @RequestMapping("/coursePlan/getCourseList")
    public ResponseResult getCourseList(String courseName);

    /**
     * 删除计划课程
     * @param id
     * @return
     */
    @RequestMapping("/coursePlan/delete/{id}")
    public ResponseResult deleteCourse(@PathVariable("id") long id);

    /**
     * 获取发布计划详细信息
     * @param id
     * @return
     */
    @RequestMapping("/userPlan/getDetails/{id}")
    public ResponseResult getPlanDetailsByPlanId(@PathVariable("id") long id);
}
