package com.ucar.qtcassist.courseware.model.DTO;

import java.io.File;
import java.io.Serializable;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月17日
 */
public class FileDTO implements Serializable {

    static final long serialVersionUID = -1;
    /**
     * 文件
     */
    private File file;
    /**
     * baseCoursewareId
     */
    private Long id;
    /**
     * 文件全名（带文件后缀  eg: test.pptx）
     * */
    private String originalFilename;
    /**
     * 文件在服务器中的绝对路径
     * */
    private String location;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
