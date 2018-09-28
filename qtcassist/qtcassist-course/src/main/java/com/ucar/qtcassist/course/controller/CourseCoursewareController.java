package com.ucar.qtcassist.course.controller;

import com.ucar.qtcassist.api.CourseCoursewareApi;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.DO.CourseCoursewareDO;
import com.ucar.qtcassist.course.service.CourseCoursewareService;
import com.ucar.qtcassist.courseware.service.CoursewareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseCoursewareController implements CourseCoursewareApi {

    @Autowired
    private CourseCoursewareService courseCoursewareService;

    @Autowired
    private CoursewareService coursewareService;

//    /**
//     * 查询某个课程课件的具体课件信息
//     * @param coursewareId
//     * @return
//     */
//    @Override
//    public Result<CoursewareDO> getCourseCourseware(Long coursewareId) {
//        CoursewareDO courseware = coursewareService.getCoursewareByCoursewareId(coursewareId);
//        return Result.getSuccessResult(courseware);
//    }
//
//    /**
//     * 查看某一门课程的所有课件的具体信息
//     * @param courseId 课程ID
//     * @return 课件列表
//     */
//    @Override
//    public Result<List<CoursewareDO>> getCourseCoursewareList(Long courseId) {
//        List<Long> coursewareIdList = courseCoursewareService.getCoursewareIdListByCourseId(courseId);
//        List<CoursewareDO> coursewareDOList = coursewareService.getCoursewareListByCoursewareIdList(coursewareIdList);
//        return Result.getSuccessResult(coursewareDOList);
//    }

    /**
     * 添加一个课程课件
     * @param courseId
     * @param coursewareId
     * @return
     */
    @RequestMapping(value = "/courseCourseware/addCourseCourseware",method = RequestMethod.POST)
    public Result addCourseCourseware(@RequestParam("courseId") Long courseId,
                               @RequestParam("coursewareId") Long coursewareId) {
        CourseCoursewareDO courseCourseware = new CourseCoursewareDO();
        courseCourseware.setCourseId(courseId);
        courseCourseware.setCoursewareId(coursewareId);
        int count = courseCoursewareService.insert(courseCourseware);
        if(count > 0) {
            return Result.getSuccessResult(1);
        } else {
            return Result.getBusinessException("0","");
        }
    }

    /**
     * 更新一个课程课件
     * @param courseId
     * @param oldCoursewareId
     * @param newCoursewareId
     * @return
     */
    @Override
    public Result updateCourseCourseware(@PathVariable("courseId")Long courseId,
                                         @PathVariable("oldCoursewareId") Long oldCoursewareId,
                                         @PathVariable("newCoursewareId") Long newCoursewareId) {
        Long id = courseCoursewareService.getIdByCourseIdAndCoursewareId(courseId, oldCoursewareId);
        CourseCoursewareDO courseCourseware = new CourseCoursewareDO();
        courseCourseware.setId(id);
        courseCourseware.setCoursewareId(newCoursewareId);
        int count = courseCoursewareService.updateByPrimaryKeySelective(courseCourseware);
        if(count > 0) {
            return Result.getSuccessResult("修改课件成功");
        } else {
            return Result.getBusinessException("修改课件失败","");
        }
    }

    /**
     * 删除一个课程课件
     * @param courseId
     * @param coursewareId
     * @return
     */
    @Override
    public Result deleteCourseCourseware(@PathVariable Long courseId, @PathVariable Long coursewareId) {
        Long id = courseCoursewareService.getIdByCourseIdAndCoursewareId(courseId, coursewareId);
        int count = courseCoursewareService.deleteByPrimaryKey(id);
        if(count > 0) {
            return Result.getSuccessResult("删除课件成功");
        } else {
            return Result.getBusinessException("删除课件失败","");
        }
    }
}
