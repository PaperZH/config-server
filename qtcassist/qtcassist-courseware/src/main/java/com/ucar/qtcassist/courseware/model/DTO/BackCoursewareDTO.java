package com.ucar.qtcassist.courseware.model.DTO;

import java.util.Date;

/**
 * 后端返回给前端的课件类
 *
 * @author shijie.xu
 * @since 2018年08月13日
 */
public class BackCoursewareDTO {
    /**
     * 课件名称
     */
    private String coursewareName;

    private String coursewareDescription;

    private Date publishTime;

    /**
     * 用户上传的原文件 url
     */
    private String sourceUrl;


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

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
