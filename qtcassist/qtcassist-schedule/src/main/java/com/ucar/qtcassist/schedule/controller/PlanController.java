package com.ucar.qtcassist.schedule.controller;

import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.schedule.model.PlanDO;
import com.ucar.qtcassist.schedule.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result add(PlanDO plan) {
        int count = planService.insertSelective(plan);
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
        PlanDO plan = planService.selectByPrimaryKey(id);
        return Result.getSuccessResult(plan);
    }

    /**
     * 更新培训计划
     * @param plan 培训计划对象
     * @return
     */
    @PostMapping("/update")
    public Result update(PlanDO plan) {
        int count = planService.updateByPrimaryKeySelective(plan);
        if(count != 0) {
            return Result.getSuccessResult("更新培训计划信息成功");
        } else {
            return Result.getBusinessException("更新培训计划信息失败", "-2");
        }
    }

}
