package com.ucar.qtc.controller;

import com.ucar.qtc.utils.ResponseResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/plan")
public class PlanController {

    /**
     * 根据教师ID获得学生信息
     *
     * @param techerId
     * @return
     */
    @GetMapping("/getStudents")
    public ResponseResult getStudents(long techerId) {
        System.out.println(techerId);
        ArrayList<Map> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("studentId",i+100);
            hashMap.put("studentName", "张山" + i);
            hashMap.put("studentAvatar", "static/image/5.jpg");
            list.add(hashMap);
        }
        return ResponseResult.data(list);
    }


    /**
     * 获取教师制定计划列表
     *
     * @param params
     * @return
     */
    @GetMapping("/getTeacherPlan")
    public ResponseResult getTeacherPlanList(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        ArrayList<Map> list = new ArrayList();
        for (int i = 0; i < 8; i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("planId",i);
            hashMap.put("studentName", "张三" + i);
            hashMap.put("planTitle", "计划名称" + i);
            hashMap.put("startDate", "2018-8-20");
            hashMap.put("endDate", "2018-8-30");
            hashMap.put("courseName", "课程名称" + i);
            hashMap.put("studentGetScore", i);
            hashMap.put("planStudentScore", 10);
            if (i % 2 == 0) {
                hashMap.put("isFinished", true);
            } else {
                hashMap.put("isFinished", false);
            }

            list.add(hashMap);
        }
        return ResponseResult.ok().put("data", list).put("total", 20).put("students", getStudents(100).get("data"));
    }

    @GetMapping("/getPublishedPlan")
    public ResponseResult getPublishedPlan(@RequestParam Map<String,Object> params){
        System.out.println(params);
        ArrayList<Map> list = new ArrayList();
        for (int i = 0; i < 8; i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("planId",i);
            hashMap.put("studentName", "张三" + i);
            hashMap.put("planTitle", "计划名称" + i);
            hashMap.put("startDate", "2018-8-20");
            hashMap.put("endDate", "2018-8-30");
            hashMap.put("studentGetScore", i);
            hashMap.put("planStudentScore", 10);
            if (i % 2 == 0) {
                hashMap.put("isFinished", true);
            } else {
                hashMap.put("isFinished", false);
            }

            list.add(hashMap);
        }
        return ResponseResult.data(list);
    }

    /**
     * 获取学生计划列表
     *
     * @param params
     * @return
     */
    @GetMapping("/getStudentPlan")
    public ResponseResult getStudentPlanList(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        ArrayList<Map> list = new ArrayList();
        for (int i = 0; i < 4; i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("planTitle", "计划名称" + i);
            hashMap.put("startDate", "2018-8-20");
            hashMap.put("endDate", "2018-8-30");
            hashMap.put("studentGetScore", i);
            hashMap.put("planStudentScore", 10);
            if (i % 2 == 0) {
                hashMap.put("isFinished", true);
            } else {
                hashMap.put("isFinished", false);
            }

            list.add(hashMap);
        }
        return ResponseResult.ok().put("data", list).put("total", 20);
    }

    @RequestMapping("/addPlan")
    public ResponseResult addPlan(@RequestBody Map<String,Object> params){
        System.out.println(params);
        ArrayList<Date> date= (ArrayList<Date>) params.get("data");
        Date startDate = date.get(0);
        Date endDate = date.get(1);
        params.remove("date");
        params.put("startDate",startDate);
        params.put("endDate", endDate);
        System.out.println(params);
        System.out.println(date.get(0));
        return ResponseResult.ok();
    }
    /**
     * 删除计划
     * @param planId
     * @return
     */
    @GetMapping("/deletePlan")
    public ResponseResult deletePlan(@RequestParam long planId){
        System.out.println(planId);
        return ResponseResult.ok("删除成功");
    }
}
