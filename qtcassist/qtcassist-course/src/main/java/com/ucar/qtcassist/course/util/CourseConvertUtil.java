package com.ucar.qtcassist.course.util;

import com.ucar.qtcassist.api.model.DO.CourseDO;
import com.ucar.qtcassist.api.model.DO.CourseTypeDO;
import com.ucar.qtcassist.api.model.VO.CourseVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CourseConvertUtil {

    public static CourseDO convertToCourseDO(CourseVO courseVO) {
        CourseDO courseDO = new CourseDO();
        courseDO.setId(courseVO.getCourseId());
        courseDO.setTypeId(courseVO.getCourseType().getId());
        courseDO.setCourseName(courseVO.getCourseName());
        courseDO.setCourseCover(courseVO.getCourseCover());
        courseDO.setCourseDescription(courseVO.getCourseDescription());
        courseDO.setCourseScore(courseVO.getCourseScore());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date invalidDate = null;
        try {
            invalidDate = sdf.parse(courseVO.getInvalidDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        courseDO.setInvalidDate(invalidDate);
        courseDO.setUpdateTime(new Date());
        return courseDO;
    }

    public static CourseVO convertToCourseVO(CourseDO courseDO, CourseTypeDO courseTypeDO) {
        CourseVO courseVO = new CourseVO();
        courseVO.setCourseId(courseDO.getId());
        courseVO.setCourseType(courseTypeDO);
        courseVO.setCourseName(courseDO.getCourseName());
        courseVO.setCourseCover(courseDO.getCourseCover());
        courseVO.setCourseDescription(courseDO.getCourseDescription());
        courseVO.setCourseScore(courseDO.getCourseScore());
        courseVO.setPraiseNum(courseDO.getPraiseNum());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        courseVO.setInvalidDate(sdf.format(courseDO.getInvalidDate()));
        courseVO.setPublishTime(sdf.format(courseDO.getPublishTime()));
        return courseVO;
    }
}
