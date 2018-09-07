package com.ucar.qtcassist.schedule.controller;

import com.ucar.qtcassist.api.common.PageResult;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.DO.PlanDO;
import com.ucar.qtcassist.schedule.dto.PlanDTO;
import com.ucar.qtcassist.schedule.dto.QueryPlanDTO;
import com.ucar.qtcassist.schedule.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    /**
     * 删除培训计划
     * @param id 培训计划id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        int count = planService.deleteByPrimaryKey(id);
        if(count != 0) {
            return Result.getSuccessResult("删除培训计划信息成功");
        } else {
            return Result.getBusinessException("删除培训计划信息失败", "-2");
        }
    }


    /**
     * 添加培训计划
     * @param plan 培训计划对象
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody PlanDTO plan) {
        int count = planService.insert(plan);
        if(count != 0) {
            return Result.getSuccessResult("添加培训计划信息成功");
        } else {
            return Result.getBusinessException("添加培训计划信息失败", "-2");
        }
    }

    /**
     * 查询培训计划
     * @param id 培训计划id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id) {
        System.out.println("asdasd");
        PlanDO plan = planService.selectByPrimaryKey(id);
        return Result.getSuccessResult(plan);
    }

    /**
     * 查询获取制定计划
     */
    @RequestMapping("/getPlan")
    public Result getPlan(@RequestBody QueryPlanDTO planDTO){
        System.out.println("daozzzzzzzzz");
        List<PlanDO> planList = planService.getPlanList(planDTO);
        Integer total = planService.getPlanTotal(planDTO);
        return PageResult.getSuccessResult(planList,total);
    }

    /**
     * 更新培训计划
     * @param plan 培训计划对象
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody PlanDTO plan) {
        System.out.println(plan);
        int count = planService.updateByPrimaryKeySelective(plan);
        System.out.println(count);
        if(count != 0) {
            return Result.getSuccessResult("更新培训计划信息成功");
        } else {
            return Result.getBusinessException("更新培训计划信息失败", "-2");
        }
    }

}
