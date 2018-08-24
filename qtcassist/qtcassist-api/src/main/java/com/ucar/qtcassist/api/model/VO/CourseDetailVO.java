package com.ucar.qtcassist.api.model.VO;

import com.ucar.qtcassist.api.model.DO.CourseDO;

public class CourseDetailVO {
    private CourseDO course;
    private TeacherVO teacher;
//    private List<CoursewareDO> coursewares;

    public CourseDO getCourse() {
        return course;
    }

    public void setCourse(CourseDO course) {
        this.course = course;
    }

    public TeacherVO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherVO teacher) {
        this.teacher = teacher;
    }

//    public List<CoursewareDO> getCoursewares() {
//        return coursewares;
//    }
//
//    public void setCoursewares(List<CoursewareDO> coursewares) {
//        this.coursewares = coursewares;
//    }
}
