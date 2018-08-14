package com.ucar.qtcassist.service;

import com.ucar.qtcassist.model.Course;

public interface CourseService {

    Course selectByPrimaryKey(Long id);

}
