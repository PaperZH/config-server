package com.ucar.qtcassist.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 上传课件时向前端请求的参数类
 *
 * @author shijie.xu
 * @since 2018年08月16日
 */
public class UploadCoursewareDTO {
    private Long typeId;

    private String coursewareName;

    private String coursewareDescription;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

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

}
