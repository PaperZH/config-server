package com.ucar.qtcassist.api.model.VO;

import com.ucar.qtcassist.api.model.DO.CourseTypeDO;

public class CourseVO {
    private Long courseId;

    private String typeName;

    private CourseTypeDO courseType;

    private String courseName;

    private String courseCover;
    //详情才有
    private String courseDescription;

    private Float courseScore;

    private Integer praiseNum;

    private Integer readNum;

    private Integer collectNum;
    //详情才有
    private String invalidDate;

    private String publishTime;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public CourseTypeDO getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseTypeDO courseType) {
        this.courseType = courseType;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCover() {
        return courseCover;
    }

    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Float getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(Float courseScore) {
        this.courseScore = courseScore;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public String getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(String invalidDate) {
        this.invalidDate = invalidDate;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }
}
