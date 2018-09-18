package com.ucar.qtcassist.courseware.model.DO;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年09月18日
 */
public class BackDO {
    private Long   id;
    private String coursewareName;
    private String coursewareDescription;
    private String coursewareType;
    private String baseCoursewareName;
    private String baseCoursewareDescription;
    private String preUrl;
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

    public String getCoursewareType() {
        return coursewareType;
    }

    public void setCoursewareType(String coursewareType) {
        this.coursewareType = coursewareType;
    }

    public String getBaseCoursewareName() {
        return baseCoursewareName;
    }

    public void setBaseCoursewareName(String baseCoursewareName) {
        this.baseCoursewareName = baseCoursewareName;
    }

    public String getBaseCoursewareDescription() {
        return baseCoursewareDescription;
    }

    public void setBaseCoursewareDescription(String baseCoursewareDescription) {
        this.baseCoursewareDescription = baseCoursewareDescription;
    }

    public String getPreUrl() {
        return preUrl;
    }

    public void setPreUrl(String preUrl) {
        this.preUrl = preUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
