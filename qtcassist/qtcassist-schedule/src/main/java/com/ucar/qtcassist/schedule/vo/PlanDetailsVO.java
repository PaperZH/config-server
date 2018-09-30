package com.ucar.qtcassist.schedule.vo;

import com.ucar.qtcassist.api.model.DO.CoursePlanDO;

import java.util.List;

/**
 * @author: cong.li
 * @date: 2018/9/29 14:18
 */
public class PlanDetailsVO {
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

    public Float getStudentGetScore() {
        return studentGetScore;
    }

    public void setStudentGetScore(Float studentGetScore) {
        this.studentGetScore = studentGetScore;
    }

    public String getStudentEvaluateContent() {
        return studentEvaluateContent;
    }

    public void setStudentEvaluateContent(String studentEvaluateContent) {
        this.studentEvaluateContent = studentEvaluateContent;
    }

    public String getStudentSummary() {
        return studentSummary;
    }

    public void setStudentSummary(String studentSummary) {
        this.studentSummary = studentSummary;
    }

    public String getTeacherEvaluateContent() {
        return teacherEvaluateContent;
    }

    public void setTeacherEvaluateContent(String teacherEvaluateContent) {
        this.teacherEvaluateContent = teacherEvaluateContent;
    }

    public Float getPlanStudentScore() {
        return planStudentScore;
    }

    public void setPlanStudentScore(Float planStudentScore) {
        this.planStudentScore = planStudentScore;
    }

    public String getPlanTitle() {
        return planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
    }

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    public String getPlanDestination() {
        return planDestination;
    }

    public void setPlanDestination(String planDestination) {
        this.planDestination = planDestination;
    }

    public Float getPlanScore() {
        return planScore;
    }

    public void setPlanScore(Float planScore) {
        this.planScore = planScore;
    }

    public List<CoursePlanDO> getCourses() {
        return courses;
    }

    public void setCourses(List<CoursePlanDO> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "PlanDetailsVO{" +
                "id=" + id +
                ", teacherName='" + teacherName + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentGetScore='" + studentGetScore + '\'' +
                ", studentEvaluateContent='" + studentEvaluateContent + '\'' +
                ", studentSummary='" + studentSummary + '\'' +
                ", teacherEvaluateContent='" + teacherEvaluateContent + '\'' +
                ", planStudentScore=" + planStudentScore +
                ", planTitle='" + planTitle + '\'' +
                ", planContent='" + planContent + '\'' +
                ", planDestination='" + planDestination + '\'' +
                ", planScore='" + planScore + '\'' +
                ", coursePlanDO=" + courses +
                '}';
    }

    private Long id;
    private String teacherName;
    private String studentName;
    private Float studentGetScore;
    private String studentEvaluateContent;
    private String studentSummary;
    private String teacherEvaluateContent;
    private Float planStudentScore;
    private String planTitle;
    private String planContent;
    private String planDestination;
    private Float planScore;
    private List<CoursePlanDO> courses;
}
