package com.ucar.qtcassist.courseware.service;

import java.io.IOException;
import java.io.InputStream;

/**
 * 远程文件操作
 *
 * @author shijie.xu
 * @since 2018年08月15日
 */
public interface RemoteFileService {
    /**
     * 将流形式的文件上传至服务器，并返回url
     */
    String uploadFile(InputStream inputStream,String Name) throws IOException;

}
