package com.ucar.qtc.service;

import com.ucar.qtc.utils.ResponseResult;

import java.util.Map;

public interface PlanService {

    /**
     * 分页获取教师制定计划列表
     * @param params    (long teacherId, String planTitle,int currentPage, int pageSize)
     * @return
     */
    public ResponseResult getTeacherPlanList(Map<String,Object> params);

    /**
     * 根据计划ID获取课程基本信息
     * @param planId
     * @return
     */
    public ResponseResult getCourseByPlanId(long planId);

    /**
     * 分页获取发布的计划列表
     * @param params
     * @return
     */
    public ResponseResult getPublishedPlanList(Map<String,Object> params);

    /**
     * 模糊查询制定的计划列表
     * @param planName
     * @return
     */
    public ResponseResult getPlanList(String planName);

    /**
     * 分页获取学员计划列表
     * @param params    (long studentId, String planTitle,int currentPage, int pageSize)
     * @return
     */
    public ResponseResult getStudentPlanList(Map<String,Object> params);

    /**
     * 根据教师ID获取学员信息.
     * @param techerId
     * @return
     */
    public ResponseResult getStudents(long techerId);

    /**
     * 增加制定计划
     * @param params
     * @return
     */
    public ResponseResult addPlan (Map<String,Object> params);

    /**
     * 增加发布的计划
     * @param params
     * @return
     */
    public ResponseResult addPublishedPlan (Map<String,Object> params);

    /**
     * 删除制定的计划
     * @param
     * @return
     */
    public ResponseResult deletePlan(long planId);

    /**
     * 删除发布的计划
     * @param planId
     * @return
     */
    public ResponseResult deletePublished(long planId);

}
