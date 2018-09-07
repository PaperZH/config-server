package com.ucar.qtcassist.api.model.VO;

public class CourseUserVO {
    private Long userId;
    private CourseVO course;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CourseVO getCourse() {
        return course;
    }

    public void setCourse(CourseVO course) {
        this.course = course;
    }
}
