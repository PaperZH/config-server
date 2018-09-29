package com.ucar.qtcassist.schedule.dto;

/**
 * @description: 制定计划结果封装类
 * @author: cong.li
 * @date: 2018/9/29 14:18
 */
public class PlanDTO {
    private Long id;
    private Long userId;
    private String planTitle;
    private String planContent;
    private String planDestination;
    private Float planScore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "PlanDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", planTitle='" + planTitle + '\'' +
                ", planContent='" + planContent + '\'' +
                ", planDestination='" + planDestination + '\'' +
                ", planScore=" + planScore +
                '}';
    }
}
