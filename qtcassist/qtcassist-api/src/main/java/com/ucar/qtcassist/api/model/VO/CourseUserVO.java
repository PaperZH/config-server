package com.ucar.qtcassist.api.model.VO;

import com.ucar.qtcassist.api.model.DO.CourseDO;

public class CourseUserVO {
    private Long userId;
    private CourseDO course;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CourseDO getCourse() {
        return course;
    }

    public void setCourse(CourseDO course) {
        this.course = course;
    }
}
