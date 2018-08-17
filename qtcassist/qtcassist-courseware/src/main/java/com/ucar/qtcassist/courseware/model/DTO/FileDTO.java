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
    /**阿
     * baseCoursewareId
     */
    private Long id;

    private String originalFilename;


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
}
