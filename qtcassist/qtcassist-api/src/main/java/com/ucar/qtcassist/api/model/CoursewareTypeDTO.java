package com.ucar.qtcassist.api.model;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年09月03日
 */
public class CoursewareTypeDTO {

    private Long id;

    private String typeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }
}
