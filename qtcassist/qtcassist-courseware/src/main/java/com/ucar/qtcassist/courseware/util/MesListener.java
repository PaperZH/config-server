package com.ucar.qtcassist.courseware.util;

import com.taobao.metamorphosis.client.extension.spring.DefaultMessageListener;
import com.taobao.metamorphosis.client.extension.spring.MetaqMessage;
import com.ucar.qtcassist.courseware.constant.FileType;
import com.ucar.qtcassist.courseware.model.DTO.FileDTO;
import com.ucar.qtcassist.courseware.service.FileService;
import com.ucar.qtcassist.courseware.service.Impl.BaseCoursewareServiceImpl;
import com.ucar.qtcassist.courseware.service.RemoteFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月17日
 */
@Component
public class MesListener extends DefaultMessageListener<FileDTO> {

    private static final String LOCATION = "qtcassist/tempFile";
    private static Logger LOGGER = LoggerFactory.getLogger(BaseCoursewareServiceImpl.class);
    @Autowired
    FileService fileService;
    @Autowired
    RemoteFileService remoteFileService;

    @Override
    public void onReceiveMessages(MetaqMessage<FileDTO> metaqMessage) {
        FileDTO fileDTO = metaqMessage.getBody();
        File file = fileDTO.getFile();
        String coursewareName = fileDTO.getOriginalFilename();
        LOGGER.error("MqListener:"+coursewareName);
        if(fileService.typeCheck(coursewareName)) {

            int point = coursewareName.lastIndexOf(".");
            //名称
            String Name = coursewareName.substring(0, point);

            String pdfLocation = LOCATION + "/" + Name + "." + FileType.PDF;
//            LOGGER.error("pdfLocation:" + pdfLocation);
            File pdfFile = new File(pdfLocation);
            File dir = new File(LOCATION);
            if(!dir.exists()) {
                dir.mkdirs();
            }

            File fPPT = file;
            File fPDF = null;
            remoteFileService.fileConvert(fPPT, fPDF);
        }
    }
}
