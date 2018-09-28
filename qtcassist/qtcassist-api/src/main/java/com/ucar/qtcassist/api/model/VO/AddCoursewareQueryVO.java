package com.ucar.qtcassist.api.model.VO;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年09月28日
 */
public class AddCoursewareQueryVO {
    private Integer pageNo;
    private Integer pageSize;
    private Long courseId;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "AddCoursewareQueryVO{" + "pageNo=" + pageNo + ", pageSize=" + pageSize + ", courseId=" + courseId + '}';
    }
}
