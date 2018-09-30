package com.ucar.qtcassist.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年09月30日
 */
public class AddCoursewareListDTO {
    private String coursewareName;
    private Long baseCoursewareId;
    private Long coursewareNum;
    @JsonFormat(timezone = "GMT+8" ,pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
    private String coursewareDescription;
    private String coursewareTypeName;
    private String sourceUrl;

    public String getCoursewareName() {
        return coursewareName;
    }

    public void setCoursewareName(String coursewareName) {
        this.coursewareName = coursewareName;
    }

    public Long getBaseCoursewareId() {
        return baseCoursewareId;
    }

    public void setBaseCoursewareId(Long baseCoursewareId) {
        this.baseCoursewareId = baseCoursewareId;
    }

    public Long getCoursewareNum() {
        return coursewareNum;
    }

    public void setCoursewareNum(Long coursewareNum) {
        this.coursewareNum = coursewareNum;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getCoursewareDescription() {
        return coursewareDescription;
    }

    public void setCoursewareDescription(String coursewareDescription) {
        this.coursewareDescription = coursewareDescription;
    }

    public String getCoursewareTypeName() {
        return coursewareTypeName;
    }

    public void setCoursewareTypeName(String coursewareTypeName) {
        this.coursewareTypeName = coursewareTypeName;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @Override
    public String toString() {
        return "AddCoursewareListDTO{" + "coursewareName='" + coursewareName + '\'' + ", baseCoursewareId=" + baseCoursewareId + ", coursewareNum=" + coursewareNum + ", publishTime=" + publishTime + ", coursewareDescription='" + coursewareDescription + '\'' + ", coursewareTypeName='" + coursewareTypeName + '\'' + ", sourceUrl='" + sourceUrl + '\'' + '}';
    }
}
