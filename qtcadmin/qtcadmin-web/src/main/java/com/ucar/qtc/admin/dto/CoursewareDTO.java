package com.ucar.qtc.admin.dto;

import java.util.Date;

/**
 * 传递给课程模块
 *
 * @author shijie.xu
 * @since 2018年08月21日
 */
public class CoursewareDTO {
    /**
     * id
     * */
    private Long id;
    /**
     * 名称
     */
    private String name;
//    /**
//     * 分类
//     */
//    private String type;
//    /**
//     * 作者
//     * */
    /**
     * 描述
     */
    private String description;
    /**
     * 发布时间
     * */
    private Date publishTime;
    /**
     *
     * */
    private String sourceUrl;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }*/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
