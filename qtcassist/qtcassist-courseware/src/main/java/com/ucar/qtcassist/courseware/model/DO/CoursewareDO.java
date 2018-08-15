package com.ucar.qtcassist.courseware.model.DO;

import java.util.Date;

public class CoursewareDO {
    private Long id;

    private Long typeId;

    private Long baseCoursewareId;

    private String coursewareName;

    private String coursewareDescription;

    private Date publishTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getBaseCoursewareId() {
        return baseCoursewareId;
    }

    public void setBaseCoursewareId(Long baseCoursewareId) {
        this.baseCoursewareId = baseCoursewareId;
    }

    public String getCoursewareName() {
        return coursewareName;
    }

    public void setCoursewareName(String coursewareName) {
        this.coursewareName = coursewareName == null ? null : coursewareName.trim();
    }

    public String getCoursewareDescription() {
        return coursewareDescription;
    }

    public void setCoursewareDescription(String coursewareDescription) {
        this.coursewareDescription = coursewareDescription == null ? null : coursewareDescription.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}