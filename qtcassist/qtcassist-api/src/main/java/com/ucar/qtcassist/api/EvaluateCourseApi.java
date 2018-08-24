package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.common.Page;
import com.ucar.qtcassist.api.model.DO.EvaluateCourseDO;
import com.ucar.qtcassist.api.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface EvaluateCourseApi {

    /**
     * 添加评价课程记录
     */
    @PostMapping("/addEvaluateCourse")
    public Result addEvaluateCourse(@RequestBody EvaluateCourseDO evaluateCourse);

    /**
     * 查询评价课程记录
     * @param evaluateCourseId 评价课程记录id
     * @return
     */
    @GetMapping("/getEvaluateCourse/{evaluateCourseId}")
    public Result<EvaluateCourseDO> getEvaluateCourse(@PathVariable("evaluateCourseId") Long evaluateCourseId);

    /**
     * 查询某门课程的所有评价记录
     * @param courseId 课程id
     * @return
     */
    @GetMapping("/getEvaluateCourse/{courseId}")
    public Result<Page<EvaluateCourseDO>> getEvaluateCourseList(@PathVariable("courseId") Long courseId);

    /**
     * 更新评价课程记录
     * @param evaluateCourse 评价课程记录对象
     * @return
     */
    @PostMapping("/updateEvaluateCourse")
    public Result updateEvaluateCourse(@RequestBody EvaluateCourseDO evaluateCourse);

    /**
     * 删除评价课程记录
     * @param evaluateCourseId 评价课程记录id
     * @return
     */
    @GetMapping("/deleteEvaluateCourse/{evaluateCourseId}")
    public Result deleteEvaluateCourse(@PathVariable("evaluateCourseId") Long evaluateCourseId);
}
