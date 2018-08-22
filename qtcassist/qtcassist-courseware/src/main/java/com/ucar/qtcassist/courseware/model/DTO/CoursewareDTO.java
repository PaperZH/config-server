package com.ucar.qtcassist.courseware.model.DTO;

/**
 * 传递给课程模块
 * @author shijie.xu
 * @since 2018年08月21日
 */
public class CoursewareDTO {
    /**
     * 名称
     * */
    private String name;
    /**
     * 分类
     * */
    private String type;
    /**
     * 作者
     * */
    /**
     * 描述
     * */
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
