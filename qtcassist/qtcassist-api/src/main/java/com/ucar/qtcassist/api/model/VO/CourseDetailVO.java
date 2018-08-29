package com.ucar.qtcassist.api.model.VO;

import com.ucar.qtcassist.api.model.CoursewareDTO;
import java.util.List;

public class CourseDetailVO {
    private CourseVO course;
    private TeacherVO teacher;
    private List<CoursewareDTO> coursewares;

    public CourseVO getCourse() {
        return course;
    }

    public void setCourse(CourseVO course) {
        this.course = course;
    }

    public TeacherVO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherVO teacher) {
        this.teacher = teacher;
    }

    public List<CoursewareDTO> getCoursewares() {
        return coursewares;
    }

    public void setCoursewares(List<CoursewareDTO> coursewares) {
        this.coursewares = coursewares;
    }
}
