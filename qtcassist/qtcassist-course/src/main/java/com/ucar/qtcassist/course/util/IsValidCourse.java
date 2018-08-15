package com.ucar.qtcassist.course.util;

import com.ucar.qtcassist.course.dao.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class IsValidCourse {

    @Autowired
    private CourseMapper courseMapper;

    public boolean isValid(Long id){
        if(courseMapper.selectByPrimaryKey(id) != null) {
            return true;
        } else {
            return false;
        }
    }
}
