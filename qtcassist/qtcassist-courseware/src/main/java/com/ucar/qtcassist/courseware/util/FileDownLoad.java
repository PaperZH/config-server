package com.ucar.qtcassist.courseware.util;

import com.ucar.qtcassist.courseware.constant.FileConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年09月14日
 */
@Component
public class FileDownLoad {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileDownLoad.class);

    public File downloadNet(String sourceUrl) {
        // 下载网络文件
        int bytesum = 0;
        int byteread = 0;

        File resFile = null;
        try {
            URL url = new URL(FileConstant.URLPREFIX + sourceUrl);
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = simpleDateFormat.format(new Date());
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            int point = sourceUrl.lastIndexOf(".");
            //后缀
            String suffix = sourceUrl.substring(point + 1, sourceUrl.length());
            LOGGER.info(suffix);
            //根据文件名后缀判断文件类型
            String loc = date+"/";
            File dir =new File(loc);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            String location = date + "/" + uuid + "." + suffix;
            resFile = new File(location);
            byte[] buffer = new byte[1204];

            try(FileOutputStream fs = new FileOutputStream(resFile)){
                while((byteread = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                }
            }
            LOGGER.info("downLoad successfully......");
            return resFile;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.error("downLoad failed......");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("downLoad failed......");
        }
        return resFile;
    }

    public static void main(String[] args) {

        String sUrl = "V2/201809/1/8-5181732ad9094c81a49c498ae22fc8e2-g-o-0.pptx";

        FileDownLoad fileDownLoad = new FileDownLoad();
        File rFile = fileDownLoad.downloadNet(sUrl);
        System.out.println(rFile);
    }
}
