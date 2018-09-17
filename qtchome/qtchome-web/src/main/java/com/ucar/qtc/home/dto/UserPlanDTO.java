package com.ucar.qtc.home.dto;

import java.util.Date;

public class UserPlanDTO {
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getPlanTitle() {
        return planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
    }



    public Float getStudentGetScore() {
        return studentGetScore;
    }

    public void setStudentGetScore(Float studentGetScore) {
        this.studentGetScore = studentGetScore;
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

    @Override
    public String toString() {
        return "UserPlanDTO{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", studentId=" + studentId +
                ", planTitle='" + planTitle + '\'' +
                ", studentGetScore=" + studentGetScore +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    private Long id;
    private Long teacherId;
    private Long studentId;
    private String planTitle;
    private Float studentGetScore;
    private Date startDate;
    private Date endDate;
}
