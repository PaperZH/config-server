package com.ucar.qtcassist.courseware.service.Impl;

import com.ucar.qtcassist.courseware.constant.FileType;
import com.ucar.qtcassist.courseware.controller.CoursewareController;
import com.ucar.qtcassist.courseware.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucar.qtcassist.base.common.exception.BusinessRuntimeException;

/**
 * 文件处理
 *
 * @author shijie.xu
 * @since 2018年08月17日
 */
@Service
public class FileServiceImpl implements FileService {

    private static Logger LOGGER = LoggerFactory.getLogger(CoursewareController.class);

    @Override
    public boolean typeCheck(String coursewareName) {
        int point = coursewareName.lastIndexOf(".");
        //名称
        String Name = coursewareName.substring(0, point);
        LOGGER.error(Name);
        //后缀
        String suffix = coursewareName.substring(point + 1, coursewareName.length());
        LOGGER.error(suffix);
        //根据文件名后缀判断文件类型
        if(suffix.equals(FileType.DOC) || suffix.equals(FileType.DOCX) || suffix.equals(FileType.PPT) || suffix.equals(FileType.PPTX)) {
            return true;
        } else {
            throw new BusinessRuntimeException("Invalid File");
        }
    }
}
