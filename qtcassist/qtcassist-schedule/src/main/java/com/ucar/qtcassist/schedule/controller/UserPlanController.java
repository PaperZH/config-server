package com.ucar.qtcassist.schedule.controller;

import com.ucar.qtcassist.api.common.PageResult;
import com.ucar.qtcassist.api.model.DO.CoursePlanDO;
import com.ucar.qtcassist.api.model.DO.PlanDO;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.DO.UserPlanDO;
import com.ucar.qtcassist.api.model.VO.StudentVO;
import com.ucar.qtcassist.course.model.UserDTO;
import com.ucar.qtcassist.course.service.AdminFeginClient;
import com.ucar.qtcassist.schedule.dto.QueryPlanDTO;
import com.ucar.qtcassist.schedule.dto.UserPlanDTO;
import com.ucar.qtcassist.schedule.dto.UserPlanListDTO;
import com.ucar.qtcassist.schedule.service.CoursePlanService;
import com.ucar.qtcassist.schedule.service.PlanService;
import com.ucar.qtcassist.schedule.service.UserPlanService;
import com.ucar.qtcassist.schedule.vo.PlanDetailsVO;
import com.ucar.qtcassist.schedule.vo.UserPlanVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
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
    @Autowired
    private AdminFeginClient adminFeginClient;

    /**
     * 删除用户培训计划关系
     *
     * @param id 用户培训计划id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        int count = userPlanService.deleteByPrimaryKey(id);
        if (count != 0) {
            return Result.getSuccessResult("删除培训计划信息成功");
        } else {
            return Result.getBusinessException("删除培训计划信息失败", "-2");
        }
    }

    /**
     * 根据teacherId获取学生列表
     *
     * @return
     */
    @RequestMapping("/getStudents/{id}")
    public Result getStudentsById(@PathVariable("id") Long id) {
        String resultStr = adminFeginClient.getStudentInfoById(id);
        JSONArray jsonObject = (JSONArray) JSONObject.fromObject(resultStr).get("data");
        if (jsonObject == null) {
            return Result.getBusinessException("暂未有学员分配", "-2");
        }
        List<UserDTO> user = (List<UserDTO>) JSONArray.toList(jsonObject, new UserDTO(), new JsonConfig());
        List<StudentVO> studentVOS = new ArrayList<>();
        for (UserDTO userDTO : user) {
            StudentVO studentVO = new StudentVO();
            BeanUtils.copyProperties(userDTO, studentVO);
            studentVOS.add(studentVO);
        }
        return Result.getSuccessResult(studentVOS);
    }

    /**
     * 根据计划ID获取详细信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getDetails/{id}")
    public Result getDetails(@PathVariable("id") Long id) {
        //获取用户计划
        UserPlanDO userPlanDO = userPlanService.selectByPrimaryKey(id);

        //获取计划基本信息
        Long planId = userPlanDO.getPlanId();
        PlanDO planDO = planService.selectByPrimaryKey(planId);

        //远程调用用户信息
        long teacherId = userPlanDO.getTeacherId();
        String studentInfo = adminFeginClient.getUserInfoById(teacherId);
        JSONObject jsonObject = (JSONObject) JSONObject.fromObject(studentInfo).get("data");
        UserDTO user = (UserDTO) JSONObject.toBean(jsonObject, UserDTO.class);

        //获取课程信息
        List<CoursePlanDO> coursePlanDOS = coursePlanService.selectByPrimaryKey(planId);
        PlanDetailsVO planDetailsVO = new PlanDetailsVO();
        BeanUtils.copyProperties(userPlanDO, planDetailsVO);
        planDetailsVO.setPlanContent(planDO.getPlanContent());
        planDetailsVO.setPlanDestination(planDO.getPlanDestination());
        planDetailsVO.setPlanScore(planDO.getPlanScore());
        planDetailsVO.setCourses(coursePlanDOS);
        planDetailsVO.setTeacherName(user.getNickname());
        return Result.getSuccessResult(planDetailsVO);
    }

    /**
     * 添加用户培训计划关系
     *
     * @param userPlan 用户培训计划关系对象
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody UserPlanListDTO userPlan) {
        int count = userPlanService.insertSelective(userPlan);
        if (count != 0) {
            return Result.getSuccessResult("添加培训计划信息成功");
        } else {
            return Result.getBusinessException("添加培训计划信息失败", "-2");
        }
    }


    /**
     * 查询获取发布计划
     */
    @RequestMapping("/getPlan")
    public Result getPlan(@RequestBody QueryPlanDTO planDTO) {
        Integer total = userPlanService.queryTotal(planDTO);
        if (total == 0) {
            return Result.getBusinessException("没有查到数据", "-2");
        }
        List<UserPlanDTO> planList = userPlanService.queryUserPlan(planDTO);
        List<UserPlanVO> userPlanVOS = new ArrayList<>();
        Long[] ids = new Long[planList.size()];
        for (int i = 0; i < planList.size(); i++) {
            UserPlanDTO plan = planList.get(i);
            ids[i] = plan.getStudentId();
        }
        //根据ids批量获取user信息
        String resultStr = adminFeginClient.getUsersInfoByIds(ids);
        JSONArray jsonObject = (JSONArray) JSONObject.fromObject(resultStr).get("data");
        List<UserDTO> users = (List<UserDTO>) JSONArray.toList(jsonObject, new UserDTO(), new JsonConfig());
        //将信息进行匹配装配
        for (UserPlanDTO plan : planList) {
            UserPlanVO userPlanVO = new UserPlanVO();
            BeanUtils.copyProperties(plan, userPlanVO);
            userPlanVO.setStudentName(getNickName(plan.getStudentId(), users));
            userPlanVOS.add(userPlanVO);
        }
        return PageResult.getSuccessResult(userPlanVOS, total);
    }

    /**
     * 更新用户培训计划关系
     *
     * @param userPlan 用户培训计划关系对象
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody UserPlanDO userPlan) {
        int count = userPlanService.updateByPrimaryKeySelective(userPlan);
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
