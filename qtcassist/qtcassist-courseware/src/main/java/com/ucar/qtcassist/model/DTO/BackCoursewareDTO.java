package com.ucar.qtcassist.model.DTO;

/**
 * 后端返回给前端的课件类
 *
 * @author shijie.xu
 * @since 2018年08月13日
 */
public class BackCoursewareDTO {
    /**
     * 课件id(base_courseware表中的id)
     */
    private Long id;
    /**
     * 课件类型
     **/
    private String typeName;
    /**
     * 课件名称
     */
    private String coursewareName;

    /*private String coursewareDescription;

    private Date publishTime;

    private Date updateTime;*/
    /**
     * 预览时PDF url
     */
    private String previewUrl;
    /**
     * 用户上传的原文件 url
     */
    private String sourceUrl;

    /*public Long getTypeId() {
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
    }*/

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
        this.typeName = typeName;
    }

    public String getCoursewareName() {
        return coursewareName;
    }

    public void setCoursewareName(String coursewareName) {
        this.coursewareName = coursewareName;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
