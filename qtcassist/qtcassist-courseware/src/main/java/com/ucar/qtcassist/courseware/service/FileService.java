package com.ucar.qtcassist.courseware.service;

/**
 * @author shijie.xu
 * @since 2018年08月16日
 */
public interface FileService {

    /**
     * 判断上传文件的类型是否是可以转换的类型
     *
     * @param coursewareName
     * @return
     */
    boolean typeCheck(String coursewareName);

}
