package com.ucar.qtcassist.schedule.model;

public class PlanDO {
    private Long id;

    private String planTitle;

    private String planContent;

    private String planDestination;

    private Float planScore;

    private Long builderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanTitle() {
        return planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle == null ? null : planTitle.trim();
    }

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent == null ? null : planContent.trim();
    }

    public String getPlanDestination() {
        return planDestination;
    }

    public void setPlanDestination(String planDestination) {
        this.planDestination = planDestination == null ? null : planDestination.trim();
    }

    public Float getPlanScore() {
        return planScore;
    }

    public void setPlanScore(Float planScore) {
        this.planScore = planScore;
    }

    public Long getBuilderId() {
        return builderId;
    }

    public void setBuilderId(Long builderId) {
        this.builderId = builderId;
    }
}