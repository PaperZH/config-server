package com.ucar.qtcassist.schedule.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserPlanVO {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    private Long id;
    private String teacherName;
    private String studentName;
    private String planTitle;
    private String studentSummary;
    private Float studentGetScore;
    private Float planScore;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
}
