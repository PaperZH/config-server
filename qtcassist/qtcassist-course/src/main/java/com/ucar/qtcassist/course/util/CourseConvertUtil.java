package com.ucar.qtcassist.course.util;

import com.ucar.qtcassist.api.model.DO.CourseDO;
import com.ucar.qtcassist.api.model.VO.CourseVO;
import java.util.Date;

public class CourseConvertUtil {

    public static CourseDO convertToCourseDO(CourseVO courseVO) {
        CourseDO courseDO = new CourseDO();
        if(courseVO.getCourseId() != null){
            courseDO.setId(courseVO.getCourseId());
        }
        if(courseVO.getCourseType() != null) {
            courseDO.setTypeId(courseVO.getCourseType().getId());
        }
        if(courseVO.getCourseName() != null) {
            courseDO.setCourseName(courseVO.getCourseName());
        }
        if(courseVO.getCourseCover() != null) {
            courseDO.setCourseCover(courseVO.getCourseCover());
        }
        if(courseVO.getCourseDescription() != null) {
            courseDO.setCourseDescription(courseVO.getCourseDescription());
        }
        if(courseVO.getCourseScore() != null) {
            courseDO.setCourseScore(courseVO.getCourseScore());
        }
        if(courseVO.getTeacherId() != null) {
            courseDO.setTeacherId(courseVO.getTeacherId());
        }
        if(courseVO.getInvalidDate() != null) {
            courseDO.setInvalidDate(courseVO.getInvalidDate());
        }
        courseDO.setUpdateTime(new Date());
        return courseDO;
    }

    public static CourseVO convertToCourseVO(CourseDO courseDO) {
        CourseVO courseVO = new CourseVO();
        if(courseDO.getId() != null) {
            courseVO.setCourseId(courseDO.getId());
        }
        if(courseDO.getCourseName() != null) {
            courseVO.setCourseName(courseDO.getCourseName());
        }
        if(courseDO.getCourseCover() != null) {
            courseVO.setCourseCover(courseDO.getCourseCover());
        }
        if(courseDO.getCourseDescription() != null) {
            courseVO.setCourseDescription(courseDO.getCourseDescription());
        }
        if(courseDO.getCourseScore() != null) {
            courseVO.setCourseScore(courseDO.getCourseScore());
        }
        if(courseDO.getTeacherId() != null) {
            courseVO.setTeacherId(courseDO.getTeacherId());
        }
        if(courseDO.getPraiseNum() != null) {
            courseVO.setPraiseNum(courseDO.getPraiseNum());
        }
        if(courseDO.getReadNum() != null) {
            courseVO.setReadNum(courseDO.getReadNum());
        }
        if(courseDO.getInvalidDate() != null) {
            courseVO.setInvalidDate(courseDO.getInvalidDate());
        }
        if(courseDO.getPublishTime() != null) {
            courseVO.setPublishTime(courseDO.getPublishTime());
        }
        if(courseDO.getInvalidDate() != null && courseDO.getDelFlag() != null) {
            if (courseDO.getDelFlag() == 0) {
                courseVO.setStatus(0);
            } else if (courseDO.getInvalidDate().after(new Date())) {
                courseVO.setStatus(1);
            } else {
                courseVO.setStatus(2);
            }
        }
        return courseVO;
    }
}
