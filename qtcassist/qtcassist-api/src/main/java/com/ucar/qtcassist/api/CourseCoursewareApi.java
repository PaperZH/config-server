package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface CourseCoursewareApi {

//    /**
//     * 查询某个课程课件的具体课件信息
//     * @param coursewareId
//     * @return
//     */
//    @GetMapping("/getCourseCourseware/{coursewareId}")
//    Result<CoursewareDO> getCourseCourseware(@PathVariable("coursewareId") Long coursewareId);
//
//    /**
//     * 查看某一门课程的所有课件的具体信息
//     * @param courseId 课程ID
//     * @return 课件列表
//     */
//    @GetMapping("/getCourseCoursewareList/{courseId}")
//    Result<List<CoursewareDO>> getCourseCoursewareList(@PathVariable("courseId") Long courseId);

    /**
     * 添加一个课程课件
     * @param courseId
     * @param coursewareId
     * @return
     */
    @GetMapping("/addCourseCourseware/{courseId}/{coursewareId}")
    Result addCourseCourseware(@PathVariable("courseId") Long courseId,
                               @PathVariable("coursewareId") Long coursewareId);

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
