package com.ucar.qtc.home.dto;

public class QueryCourseDTO {
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
        return "QueryCourseDTO{" +
                "courseName='" + courseName + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", offset=" + offset +
                '}';
    }

    private String courseName;
    private Integer currentPage;
    private Integer pageSize;
    private Integer offset;
}
