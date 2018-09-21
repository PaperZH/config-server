package com.ucar.qtcassist.schedule.vo;

public class PlanVO {
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

    public String getBuilderName() {
        return builderName;
    }

    public void setBuilderName(String builderName) {
        this.builderName = builderName;
    }

    @Override
    public String toString() {
        return "PlanVO{" +
                "id=" + id +
                ", planTitle='" + planTitle + '\'' +
                ", planContent='" + planContent + '\'' +
                ", planDestination='" + planDestination + '\'' +
                ", builderName='" + builderName + '\'' +
                '}';
    }

    private Long id;
    private String planTitle;
    private String planContent;
    private String planDestination;
    private String builderName;

}
