package com.ucar.qtcassist.schedule.dto;

import java.util.Date;

/**
 * @description: 查询结果封装类
 * @author: cong.li
 * @date: 2018/9/29 14:18
 */
public class UserPlanDTO {
    private Long id;
    private Long teacherId;
    private Long studentId;
    private String planTitle;
    private String studentSummary;
    private Float studentGetScore;
    private Float planScore;
    private Date startDate;
    private Date endDate;

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

    public String getStudentSummary() {
        return studentSummary;
    }

    public void setStudentSummary(String studentSummary) {
        this.studentSummary = studentSummary;
    }

    public Float getPlanScore() {
        return planScore;
    }

    public void setPlanScore(Float planScore) {
        this.planScore = planScore;
    }

    @Override
    public String toString() {
        return "UserPlanDTO{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", studentId=" + studentId +
                ", planTitle='" + planTitle + '\'' +
                ", studentSummary='" + studentSummary + '\'' +
                ", studentGetScore=" + studentGetScore +
                ", planScore=" + planScore +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
