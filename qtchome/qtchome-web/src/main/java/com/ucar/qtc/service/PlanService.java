package com.ucar.qtc.service;

import com.ucar.qtc.utils.ResponseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
@FeignClient("QTCASSIST")
public interface PlanService {

    /**
     * 分页获取教师制定计划列表
     * @param params    (long teacherId, String planTitle,int currentPage, int pageSize)
     * @return
     */
    @PostMapping("/plan/getPlan")
    public ResponseResult getTeacherPlanList(Map<String,Object> params);

    /**
     * 根据发布计划ID获取课程基本信息
     * @param planId
     * @return
     */
    @RequestMapping("/coursePlan/get/{planId}")
    public ResponseResult getCourseByPlanId(@PathVariable("planId") long planId);

    /**
     * 根据发布计划ID获取详细信息（包括教师信息，课程信息，制定计划基本信息，发布计划的基本信息）
     * @param planId
     * @return
     */
    @RequestMapping("/userPlan/getDetails/{planId}")
    public ResponseResult getPlanDetailsByPlanId(@PathVariable("planId") long planId);

    /**
     * 分页获取发布的计划列表
     * @param params
     * @return
     */
    @RequestMapping("/userPlan/getPlan")
    public ResponseResult getPublishedPlanList(Map<String,Object> params);

    /**
     * 模糊查询课程
     */
    @GetMapping("/coursePlan/getCourseList")
    public ResponseResult getCourseList(String courseName);
//
//    /**
//     * 模糊查询制定的计划列表
//     * @param planName
//     * @return
//     */
//    public ResponseResult getPlanList(String planName);
//
//    /**
//     * 分页获取学员计划列表
//     * @param params    (long studentId, String planTitle,int currentPage, int pageSize)
//     * @return
//     */
//    public ResponseResult getStudentPlanList(Map<String,Object> params);
//
//    /**
//     * 根据教师ID获取学员信息.
//     * @param techerId
//     * @return
//     */
//    public ResponseResult getStudents(long techerId);
//
    /**
     * 增加制定计划
     * @param params
     * @return
     */
    @RequestMapping("/plan/add")
    public ResponseResult addPlan (Map<String,Object> params);
//
    /**
     * 更新制定计划
     * @param params
     * @return
     */
    @RequestMapping("/plan/update")
    public ResponseResult updatePlan(Map<String,Object> params);

    /**
     * 增加发布的计划
     * @param params
     * @return
     */
    @RequestMapping("/userPlan/add")
    public ResponseResult addPublishedPlan (Map<String,Object> params);

    /**
     * 更新发布的计划
     * @param params
     * @return
     */
    @RequestMapping("/userPlan/update")
    public ResponseResult updatePublishedPlan(Map<String,Object> params);

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
     *
     * @param params
     * @return
     */
    @RequestMapping("/coursePlan/add")
    public ResponseResult addCoursePlan(Map<String,Object> params);

    /**
     * 删除计划课程
     * @param id
     * @return
     */
    @RequestMapping("/coursePlan/delete/{id}")
    public ResponseResult deleteCoursePlan(@PathVariable("id") long id);

}
