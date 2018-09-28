package com.ucar.qtcassist.api.model;

import java.util.List;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年09月28日
 */
public class AddCoursewarePageListDTO {
    /**
     * 课件列表
     * */
    private List<CoursewareDTO> coursewareDTOList;
    /**
     * 总条数
     * */
    private Long count;

    public List<CoursewareDTO> getCoursewareDTOList() {
        return coursewareDTOList;
    }

    public void setCoursewareDTOList(List<CoursewareDTO> coursewareDTOList) {
        this.coursewareDTOList = coursewareDTOList;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "AddCoursewarePageListDTO{" + "coursewareDTOList=" + coursewareDTOList + ", count=" + count + '}';
    }
}
