package com.ucar.qtcassist.schedule.controller;

import com.ucar.qtcassist.api.common.PageResult;
import com.ucar.qtcassist.api.model.DO.CoursePlanDO;
import com.ucar.qtcassist.api.model.DO.PlanDO;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.DO.UserPlanDO;
import com.ucar.qtcassist.schedule.dto.QueryPlanDTO;
import com.ucar.qtcassist.schedule.dto.UserPlanDTO;
import com.ucar.qtcassist.schedule.dto.UserPlanListDTO;
import com.ucar.qtcassist.schedule.service.CoursePlanService;
import com.ucar.qtcassist.schedule.service.PlanService;
import com.ucar.qtcassist.schedule.service.UserPlanService;
import com.ucar.qtcassist.schedule.vo.PlanDetailsVO;
import com.ucar.qtcassist.schedule.vo.UserPlanVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userPlan")
public class UserPlanController {

    @Autowired
    private UserPlanService userPlanService;

    @Autowired
    private CoursePlanService coursePlanService;
    @Autowired
    private PlanService planService;
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
     * 根据计划ID获取详细信息
     * @param id
     * @return
     */
    @GetMapping("/getDetails/{id}")
    public Result getDetails(@PathVariable("id") Long id){
        System.out.println(id);
        //获取用户计划
        UserPlanDO userPlanDO = userPlanService.selectByPrimaryKey(id);

        //获取计划基本信息
        Long planId = userPlanDO.getPlanId();
        PlanDO planDO = planService.selectByPrimaryKey(planId);

        //远程调用用户信息
//        long studentId = userPlanDO.getStudentId();
//        userPlanVO.setStudentName("学生1");
//        long teacherId = userPlanDO.getTeacherId();
//        userPlanVO.setTeacherName("导师1");

        //获取课程信息
        List<CoursePlanDO> coursePlanDOS = coursePlanService.selectByPrimaryKey(planId);

        PlanDetailsVO planDetailsVO = new PlanDetailsVO();
        BeanUtils.copyProperties(userPlanDO,planDetailsVO);
        planDetailsVO.setPlanContent(planDO.getPlanContent());
        planDetailsVO.setPlanDestination(planDO.getPlanDestination());
        planDetailsVO.setPlanScore(planDO.getPlanScore());
        planDetailsVO.setCourses(coursePlanDOS);
        return Result.getSuccessResult(planDetailsVO);
    }

    /**
     * 添加用户培训计划关系
     * @param userPlan 用户培训计划关系对象
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody UserPlanListDTO userPlan) {
        System.out.println(userPlan);
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
     * 查询获取发布计划
     */
    @RequestMapping("/getPlan")
    public Result getPlan(@RequestBody QueryPlanDTO planDTO){
        System.out.println("daozaaaaaaaaaa");
        Integer total = userPlanService.queryTotal(planDTO);
            List<UserPlanDTO> planList = userPlanService.queryUserPlan(planDTO);
            List<UserPlanVO> userPlanVOS = new ArrayList<>();
            for (UserPlanDTO plan : planList
                    ) {
                UserPlanVO userPlanVO = new UserPlanVO();
                BeanUtils.copyProperties(plan, userPlanVO);
                //微服务查询User姓名
                    long studentId = plan.getStudentId();
                    userPlanVO.setStudentName("学生1");
                    long teacherId = plan.getTeacherId();
                    userPlanVO.setTeacherName("导师1");
                userPlanVOS.add(userPlanVO);
            }
            return PageResult.getSuccessResult(userPlanVOS, total);
    }

    /**
     * 更新用户培训计划关系
     * @param userPlan 用户培训计划关系对象
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody UserPlanDO userPlan) {
        System.out.println(userPlan);
        int count = userPlanService.updateByPrimaryKeySelective(userPlan);
        if(count != 0) {
            return Result.getSuccessResult("更新培训计划信息成功");
        } else {
            return Result.getBusinessException("更新培训计划信息失败", "-2");
        }
    }
}
