package com.ucar.qtc.home.dto;

import java.util.Date;

public class UserPlanListDTO {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long[] getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(Long[] studentIds) {
        this.studentIds = studentIds;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    @Override
    public String toString() {
        return "UserPlanListDTO{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", studentIds=" + studentIds +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", planId=" + planId +
                '}';
    }

    private Long id;
    private Long teacherId;
    private Long[] studentIds;
    private Date startDate;
    private Date endDate;
    private Long planId;

}
