package com.ucar.qtcassist.schedule.controller;

import com.ucar.qtcassist.api.common.PageResult;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.DO.CourseDO;
import com.ucar.qtcassist.course.model.UserDTO;
import com.ucar.qtcassist.course.service.AdminFeginClient;
import com.ucar.qtcassist.course.service.CourseService;
import com.ucar.qtcassist.api.model.DO.CoursePlanDO;
import com.ucar.qtcassist.schedule.dto.CoursePlanDTO;
import com.ucar.qtcassist.schedule.dto.QueryCourseDTO;
import com.ucar.qtcassist.schedule.service.CoursePlanService;
import com.ucar.qtcassist.schedule.vo.CoursePlanVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/coursePlan")
public class CoursePlanController {

    @Autowired
    private CoursePlanService coursePlanService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AdminFeginClient adminFeginClient;
    /**
     * 删除课程计划关系
     * @param id 计划课程id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        int count = coursePlanService.deleteByPrimaryKey(id);
        if(count != 0) {
            return Result.getSuccessResult("删除课程计划关联信息成功");
        } else {
            return Result.getBusinessException("删除课程计划关联信息失败", "-2");
        }
    }

    /**
     * 添加课程计划关系
     * @param coursePlanDTO
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody CoursePlanDTO coursePlanDTO) {
        int count = coursePlanService.insertSelective(coursePlanDTO);
        if(count != 0) {
            List<CoursePlanDO> coursePlanDO = coursePlanService.selectByPrimaryKey(coursePlanDTO.getPlanId());
            return Result.getSuccessResult(coursePlanDO);
        } else {
            return Result.getBusinessException("添加课程课件关联信息失败", "-2");
        }
    }

    /**
     * 查询课程计划关系
     * @param id 计划课程id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id) {
        List<CoursePlanDO> coursePlanDO = coursePlanService.selectByPrimaryKey(id);
        return Result.getSuccessResult(coursePlanDO);
    }

    @RequestMapping("/getCourseList")
    public Result get(@RequestBody QueryCourseDTO courseDTO){
        int total = coursePlanService.selectTotal(courseDTO);
        if(total==0){
            return Result.getBusinessException("没有查到数据","-2");
        }else {
            List<CoursePlanDO> coursePlanDOS = coursePlanService.selectByCourseName(courseDTO);
            List<CoursePlanVO> coursePlanVOS = new ArrayList<>();
            Long[] ids = new Long[coursePlanDOS.size()];
            for (int i = 0; i < coursePlanDOS.size(); i++) {
                CoursePlanDO coursePlanDO = coursePlanDOS.get(i);
                ids[i]=coursePlanDO.getTeacherId();
            }
            //根据ids批量获取user信息
            String resultStr = adminFeginClient.getUsersInfoByIds(ids);
            JSONArray jsonObject= (JSONArray) JSONObject.fromObject(resultStr).get("data");
            List<UserDTO> users = (List<UserDTO>) JSONArray.toList(jsonObject,new UserDTO(),new JsonConfig());
            for (CoursePlanDO coursePlanDO:coursePlanDOS) {
                CoursePlanVO coursePlanVO = new CoursePlanVO();
                BeanUtils.copyProperties(coursePlanDO, coursePlanVO);
                    coursePlanVO.setTeacherName(getNickName(coursePlanDO.getTeacherId(),users));
                coursePlanVOS.add(coursePlanVO);
            }
            return PageResult.getSuccessResult(coursePlanVOS, total);
        }
    }

    /**
     * 更新课程计划关系
     * @param coursePlan 计划课程对象
     * @return
     */
    @PostMapping("/update")
    public Result update(CoursePlanDO coursePlan) {
        int count = coursePlanService.updateByPrimaryKeySelective(coursePlan);
        if(count != 0) {
            return Result.getSuccessResult("更新课程课件关联信息成功");
        } else {
            return Result.getBusinessException("更新课程课件关联信息失败", "-2");
        }
    }

    @GetMapping("/getCourse/{id}")
    public CourseDO getCourse(@PathVariable("id") Long id) {
        CourseDO course = courseService.selectByPrimaryKey(id);
        return course;
    }

    public String getNickName(Long userId,List<UserDTO> users){
        for (UserDTO user : users) {
            if (userId.equals(user.getUserId())){
                return user.getNickname();
            }
        }
        return null;
    }
}
