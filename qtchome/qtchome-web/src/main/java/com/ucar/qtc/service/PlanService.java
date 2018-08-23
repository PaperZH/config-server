package com.ucar.qtc.service;

import com.ucar.qtc.utils.ResponseResult;

import java.util.Map;

public interface PlanService {

    /**
     * 分页获取教师制定计划列表
     * @param params    (long userId, String planTitle,Date startTime, Date endTime,int currentPage, int pageSize)
     * @return
     */
    ResponseResult getTeacherPlanList(Map<String,Object> params);

    /**
     * 分页获取学员计划列表
     * @param params    (long userId, String planTitle,Date startTime, Date endTime,int currentPage, int pageSize)
     * @return
     */
    ResponseResult getStudentPlanList(Map<String,Object> params);

    ResponseResult addPlan (Map<String,Object> params);
    /**
     * 删除制定的计划表
     * @param params (long planId)
     * @return
     */
    ResponseResult deletePlan(Map<String, Object> params);
}
