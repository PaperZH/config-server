package com.ucar.qtcassist.base.common.dto;

import java.util.Date;
import java.util.List;

public class QueryDO {
    private Long userId;

    private Long courseId;

    private Long[] courseIds;

    private String courseName;

    private String orderType;

    private Date startDate;

    private Date endDate;

    private Integer startIndex;

    private Integer pageSize;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long[] getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(Long[] courseIds) {
        this.courseIds = courseIds;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
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

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setCourseIdsFromList(List<Long> courseIdList) {
        this.courseIds = new Long[courseIdList.size()];
        courseIdList.toArray(this.courseIds);
    }
}
