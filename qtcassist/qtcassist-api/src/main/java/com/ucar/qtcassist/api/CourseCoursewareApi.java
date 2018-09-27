package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.model.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CourseCoursewareApi {


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
