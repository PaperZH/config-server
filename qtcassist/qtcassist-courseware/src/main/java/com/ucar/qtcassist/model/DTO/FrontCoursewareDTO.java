package com.ucar.qtcassist.model.DTO;

import java.util.Date;

/**
 * 前端返回给后端的课件类
 *
 * @author shijie.xu
 * @since 2018年08月13日
 */
public class FrontCoursewareDTO {
    /**
     * Base_Courseware中的id
     */
    private Long baseCoursewareId;
    /**
     * 课件类型
     */
    private Long typeId;

    /*
     * 课件在课件元库中所对应的id
     *
    private Integer baseCoursewareId;*/
    /**
     * 课件名称
     */
    private String coursewareName;
    /**
     * 课件描述
     */
    private String coursewareDescription;
    /**
     * 发布时间
     */
    private Date publishTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getBaseCoursewareId() {
        return baseCoursewareId;
    }

    public void setBaseCoursewareId(Long baseCoursewareId) {
        this.baseCoursewareId = baseCoursewareId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }


    public String getCoursewareName() {
        return coursewareName;
    }

    public void setCoursewareName(String coursewareName) {
        this.coursewareName = coursewareName;
    }

    public String getCoursewareDescription() {
        return coursewareDescription;
    }

    public void setCoursewareDescription(String coursewareDescription) {
        this.coursewareDescription = coursewareDescription;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
