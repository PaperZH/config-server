package com.ucar.qtcassist.schedule.controller;

import com.ucar.qtcassist.base.model.Result;
import com.ucar.qtcassist.schedule.model.UserPlanDO;
import com.ucar.qtcassist.schedule.service.UserPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userPlan")
public class UserPlanController {

    @Autowired
    private UserPlanService userPlanService;

    /**
     * 删除用户培训计划关系
     * @param id 用户培训计划id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        int count = userPlanService.deleteByPrimaryKey(id);
        if(count != 0) {
            return Result.getSuccessResult("删除培训计划信息成功");
        } else {
            return Result.getBusinessException("删除培训计划信息失败", "-2");
        }
    }

    /**
     * 添加用户培训计划关系
     * @param userPlan 用户培训计划关系对象
     * @return
     */
    @PostMapping("/add")
    public Result add(UserPlanDO userPlan) {
        int count = userPlanService.insertSelective(userPlan);
        if(count != 0) {
            return Result.getSuccessResult("添加培训计划信息成功");
        } else {
            return Result.getBusinessException("添加培训计划信息失败", "-2");
        }
    }

    /**
     * 查询用户培训计划关系
     * @param id 用户培训计划关系id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id) {
        UserPlanDO userPlan = userPlanService.selectByPrimaryKey(id);
        return Result.getSuccessResult(userPlan);
    }

    /**
     * 更新用户培训计划关系
     * @param userPlan 用户培训计划关系对象
     * @return
     */
    @PostMapping("/update")
    public Result update(UserPlanDO userPlan) {
        int count = userPlanService.updateByPrimaryKeySelective(userPlan);
        if(count != 0) {
            return Result.getSuccessResult("更新培训计划信息成功");
        } else {
            return Result.getBusinessException("更新培训计划信息失败", "-2");
        }
    }
}
