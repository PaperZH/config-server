package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.Course;

public interface CourseService {

    Course selectByPrimaryKey(Long id);

}
