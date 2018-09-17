package com.ucar.qtcassist.api.model.DO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CourseDO {
    private Long id;

    private Long typeId;

    private String courseName;

    private String courseCover;

    private String courseDescription;

    private Float courseScore;

    private Integer readNum;

    private Integer praiseNum;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date invalidDate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Byte delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getCourseCover() {
        return courseCover;
    }

    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover == null ? null : courseCover.trim();
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription == null ? null : courseDescription.trim();
    }

    public Float getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(Float courseScore) {
        this.courseScore = courseScore;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
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

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }
}