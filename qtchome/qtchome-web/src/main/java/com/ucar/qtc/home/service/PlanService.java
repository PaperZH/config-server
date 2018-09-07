package com.ucar.qtc.home.service;

import com.ucar.qtc.home.utils.ResponseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
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
     * 分页获取发布的计划列表
     * @param params
     * @return
     */
    @RequestMapping("/userPlan/getPlan")
    public ResponseResult getPublishedPlanList(Map<String,Object> params);

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
}
