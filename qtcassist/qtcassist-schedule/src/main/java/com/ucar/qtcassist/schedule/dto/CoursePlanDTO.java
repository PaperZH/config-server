package com.ucar.qtcassist.schedule.dto;

import java.util.Arrays;

public class CoursePlanDTO {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Long[] getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(Long[] courseIds) {
        this.courseIds = courseIds;
    }

    @Override
    public String toString() {
        return "CoursePlanDTO{" +
                "id=" + id +
                ", planId=" + planId +
                ", courseIds=" + Arrays.toString(courseIds) +
                '}';
    }

    private Long id;
    private Long planId;
    private Long[] courseIds;
}
