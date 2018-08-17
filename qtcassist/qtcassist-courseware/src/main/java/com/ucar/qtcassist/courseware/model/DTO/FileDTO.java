package com.ucar.qtcassist.courseware.model.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月17日
 */
public class FileDTO {
    /**
     * 文件
     * */
    private MultipartFile file;
    /**
     * baseCoursewareId
     * */
    private Long id;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
