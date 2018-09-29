package com.ucar.qtcassist.schedule.controller;

import com.ucar.qtcassist.api.common.PageResult;
import com.ucar.qtcassist.api.model.DO.PlanDO;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.course.model.UserDTO;
import com.ucar.qtcassist.course.service.AdminFeginClient;
import com.ucar.qtcassist.schedule.dto.PlanDTO;
import com.ucar.qtcassist.schedule.dto.QueryPlanDTO;
import com.ucar.qtcassist.schedule.service.PlanService;
import com.ucar.qtcassist.schedule.vo.PlanVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: cong.li
 * @date: 2018/9/29 14:18
 */
@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanService planService;
    @Autowired
    private AdminFeginClient adminFeginClient;

    /**
     * 删除培训计划
     *
     * @param id 培训计划id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        int count = planService.deleteByPrimaryKey(id);
        if (count != 0) {
            return Result.getSuccessResult("删除培训计划信息成功");
        } else {
            return Result.getBusinessException("删除培训计划信息失败", "-2");
        }
    }


    /**
     * 添加培训计划
     *
     * @param plan 培训计划对象
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody PlanDTO plan) {
        int count = planService.insert(plan);
        if (count != 0) {
            return Result.getSuccessResult("添加培训计划信息成功");
        } else {
            return Result.getBusinessException("添加培训计划信息失败", "-2");
        }
    }


    /**
     * 查询获取制定计划
     */
    @RequestMapping("/getPlan")
    public Result getPlan(@RequestBody QueryPlanDTO planDTO) {
        List<PlanDO> planList = planService.getPlanList(planDTO);
        Integer total = planService.getPlanTotal(planDTO);
        if (total == 0) {
            return Result.getBusinessException("没有查到数据", "-2");
        }
        List<PlanVO> planVOS = new ArrayList<>();
        Long[] ids = new Long[planList.size()];
        for (int i = 0; i < planList.size(); i++) {
            PlanDO plan = planList.get(i);
            ids[i] = plan.getBuilderId();
        }
        //根据ids批量获取user信息
        String resultStr = adminFeginClient.getUsersInfoByIds(ids);
        JSONArray jsonObject = (JSONArray) JSONObject.fromObject(resultStr).get("data");
        List<UserDTO> users = (List<UserDTO>) JSONArray.toList(jsonObject, new UserDTO(), new JsonConfig());
        for (PlanDO planDO : planList) {
            PlanVO planVO = new PlanVO();
            BeanUtils.copyProperties(planDO, planVO);
            planVO.setBuilderName(getNickName(planDO.getBuilderId(), users));
            planVOS.add(planVO);
        }
        return PageResult.getSuccessResult(planVOS, total);
    }

    /**
     * 更新培训计划
     *
     * @param plan 培训计划对象
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody PlanDTO plan) {
        int count = planService.updateByPrimaryKeySelective(plan);
        if (count != 0) {
            return Result.getSuccessResult("更新培训计划信息成功");
        } else {
            return Result.getBusinessException("更新培训计划信息失败", "-2");
        }
    }

    public String getNickName(Long userId, List<UserDTO> users) {
        for (UserDTO user : users) {
            if (userId.equals(user.getUserId())) {
                return user.getNickname();
            }
        }
        return null;
    }
}
