package com.ucar.qtc.home.service;

import com.ucar.qtcassist.api.CourseCoursewareApi;
import com.ucar.qtcassist.api.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年09月05日
 */
@FeignClient(name = "qtcassist" ,path = "/courseCourseware")
public interface CourseCoursewareService extends CourseCoursewareApi {
    /**
     * 添加一个课程课件
     * @param courseId
     * @param coursewareId
     * @return
     */
    @RequestMapping(value = "/addCourseCourseware",method = RequestMethod.POST)
    Result addCourseCourseware(@RequestParam("courseId") Long courseId,
                               @RequestParam("coursewareId") Long coursewareId);

    /**
     * 更新一个课程课件
     * @param courseId
     * @param oldCoursewareId
     * @param newCoursewareId
     * @return
     */
    @GetMapping("/updateCourseCourseware/{courseId}/{oldCoursewareId}/{newCoursewareId}")
    Result updateCourseCourseware(@PathVariable("courseId") Long courseId,
                                  @PathVariable("oldCoursewareId") Long oldCoursewareId,
                                  @PathVariable("newCoursewareId") Long newCoursewareId);

    /**
     * 删除一个课程课件
     * @param courseId
     * @param coursewareId
     * @return
     */
    @GetMapping("/deleteCourseCourseware/{courseId}/{coursewareId}")
    Result deleteCourseCourseware(@PathVariable("courseId") Long courseId,
                                  @PathVariable("coursewareId") Long coursewareId);
}
