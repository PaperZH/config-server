package com.ucar.qtcassist.model.DTO;

/**
 * 用于向前端返回系统库中所有课件信息的列表实例
 *
 * @author shijie.xu
 * @since 2018年08月14日
 */
public class BaseCoursewareListDTO {
    /**
     * id
     * */
    private Long id;
    /**
     * 课件名称
     * */
    private String coursewareName;
    /**
     * 课件类型
     * */
    private String typeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoursewareName() {
        return coursewareName;
    }

    public void setCoursewareName(String coursewareName) {
        this.coursewareName = coursewareName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
