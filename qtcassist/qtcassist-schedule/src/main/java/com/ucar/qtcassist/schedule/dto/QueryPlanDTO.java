package com.ucar.qtcassist.schedule.dto;

/**
 * @description: 查询计划参数封装类
 * @author: cong.li
 * @date: 2018/9/29 14:18
 */
public class QueryPlanDTO {
    private Long userId;
    private Long teacherId;
    private Long studentId;
    private String planTitle;
    private Integer currentPage;
    private Integer pageSize;
    private Integer offset;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getPlanTitle() {
        return planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        if (offset != null) {
            return this.offset;
        }
        if (currentPage == null) {
            return null;
        }
        return (currentPage - 1) * pageSize;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "QueryPlanDTO{" +
                "userId=" + userId +
                ", teacherId=" + teacherId +
                ", studentId=" + studentId +
                ", planTitle='" + planTitle + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", offset=" + offset +
                '}';
    }
}
