package com.ucar.qtcassist.course.service;

import com.ucar.qtcassist.course.model.CreateUserCourse;

public interface CreateUserCourseService {

    CreateUserCourse selectByCourseId(Long courseId);

}
