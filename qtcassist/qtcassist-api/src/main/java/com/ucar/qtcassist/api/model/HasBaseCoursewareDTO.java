package com.ucar.qtcassist.api.model;

/**
 * 用来判断选择的课件（BaseCourseware）是否重复
 *
 * @author shijie.xu
 * @since 2018年09月29日
 */
public class HasBaseCoursewareDTO {
    private Long courseId;
    private Long baseCoursewareId;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getBaseCoursewareId() {
        return baseCoursewareId;
    }

    public void setBaseCoursewareId(Long baseCoursewareId) {
        this.baseCoursewareId = baseCoursewareId;
    }

    @Override
    public String toString() {
        return "HasBaseCoursewareDTO{" + "courseId=" + courseId + ", baseCoursewareId=" + baseCoursewareId + '}';
    }
}
