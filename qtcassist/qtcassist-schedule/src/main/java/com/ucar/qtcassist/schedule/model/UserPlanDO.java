package com.ucar.qtcassist.schedule.model;

import java.util.Date;

public class UserPlanDO {
    private Long id;

    private Long teacherId;

    private Long studentId;

    private Long planId;

    private String studentEvaluateContent;

    private Float studentGetScore;

    private String studentSummary;

    private String teacherEvaluateContent;

    private Float planStudentScore;

    private Date startDate;

    private Date endDate;

    private Byte delFlag;

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

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getStudentEvaluateContent() {
        return studentEvaluateContent;
    }

    public void setStudentEvaluateContent(String studentEvaluateContent) {
        this.studentEvaluateContent = studentEvaluateContent == null ? null : studentEvaluateContent.trim();
    }

    public Float getStudentGetScore() {
        return studentGetScore;
    }

    public void setStudentGetScore(Float studentGetScore) {
        this.studentGetScore = studentGetScore;
    }

    public String getStudentSummary() {
        return studentSummary;
    }

    public void setStudentSummary(String studentSummary) {
        this.studentSummary = studentSummary == null ? null : studentSummary.trim();
    }

    public String getTeacherEvaluateContent() {
        return teacherEvaluateContent;
    }

    public void setTeacherEvaluateContent(String teacherEvaluateContent) {
        this.teacherEvaluateContent = teacherEvaluateContent == null ? null : teacherEvaluateContent.trim();
    }

    public Float getPlanStudentScore() {
        return planStudentScore;
    }

    public void setPlanStudentScore(Float planStudentScore) {
        this.planStudentScore = planStudentScore;
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

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }
}