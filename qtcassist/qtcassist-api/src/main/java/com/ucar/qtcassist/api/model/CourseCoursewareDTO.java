package com.ucar.qtcassist.api.model;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年09月06日
 *
 * hour: '',
 * name: '',
 * typeId: '',
 * describe: '',
 * fileUrl: '',
 * baseCoursewareId: '',
 * courseId:'2'
 */
public class CourseCoursewareDTO {
    private Long hour;

    private String name;

    private Long typeId;

    private String describe;

    private String fileUrl;

    private Long baseCoursewareId;

    private Long courseId;

    public Long getHour() {
        return hour;
    }

    public void setHour(Long hour) {
        this.hour = hour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Long getBaseCoursewareId() {
        return baseCoursewareId;
    }

    public void setBaseCoursewareId(Long baseCoursewareId) {
        this.baseCoursewareId = baseCoursewareId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
