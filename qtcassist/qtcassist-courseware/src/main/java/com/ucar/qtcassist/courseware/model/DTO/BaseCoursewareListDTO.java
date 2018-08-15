package com.ucar.qtcassist.courseware.model.DTO;

/**
 * 用于向前端返回系统库中所有课件信息的
 * 列表实例
 *
 * @author shijie.xu
 * @since 2018年08月14日
 */
public class BaseCoursewareListDTO {
    /**
     * base_courseware表中的id
     */
    private Long id;
    /**
     * 课件名称
     */
    private String coursewareName;
    /**
     * 课件类型
     */
    private String typeName;
    /**
     * 课件类型id
     */
    private Long typeId;

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

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
