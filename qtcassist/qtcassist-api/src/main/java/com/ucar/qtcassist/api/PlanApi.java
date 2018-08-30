package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.model.DO.PlanDO;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.VO.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PlanApi {

    /**
     * 查询培训计划
     * @param query (long userId, String planTitle, int currentPage, int pageSize)
     * @return
     */
    @GetMapping("/getPlanList")
    Result<Page<PlanDO>> getPlanList(@RequestBody Query query);

    /**
     * 查询培训计划
     * @param planId 培训计划id
     * @return
     */
    @GetMapping("/getPlanDetail/{planId}")
    Result getPlanDetail(@PathVariable("planId") Long planId);


    /**
     * 添加培训计划
     * @param plan 培训计划对象
     * @return
     */
    @PostMapping("/addPlan")
    Result addPlan(@RequestBody PlanDO plan);

    /**
     * 更新培训计划
     * @param plan 培训计划对象
     * @return
     */
    @PostMapping("/updatePlan")
    Result updatePlan(@RequestBody PlanDO plan);

    /**
     * 删除培训计划
     * @param userId 用户id
     * @param planId 培训计划id
     * @return
     */
    @GetMapping("/deletePlan/{userId}/{planId}")
    Result deletePlan(@PathVariable("userId") Long userId, @PathVariable("planId") Long planId);
}
