package com.ucar.qtcassist.service;

import com.ucar.qtcassist.model.CreateUserCourse;

public interface CreateUserCourseService {

    CreateUserCourse selectByCourseId(Long courseId);

}
