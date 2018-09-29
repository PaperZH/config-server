package com.ucar.qtcassist.schedule.dto;

import java.util.Arrays;

/**
 * @description: 增加计划课程参数封装类
 * @author: cong.li
 * @date: 2018/9/29 14:18
 */
public class CoursePlanDTO {
    private Long id;
    private Long planId;
    private Long[] courseIds;

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
}
