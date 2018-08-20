package com.ucar.qtcassist.courseware.util;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.consumer.MessageListener;
import com.taobao.metamorphosis.client.extension.spring.DefaultMessageListener;
import com.taobao.metamorphosis.client.extension.spring.MetaqMessage;
import com.ucar.qtcassist.courseware.constant.FileType;
import com.ucar.qtcassist.courseware.model.DTO.FileDTO;
import com.ucar.qtcassist.courseware.service.FileService;
import com.ucar.qtcassist.courseware.service.Impl.BaseCoursewareServiceImpl;
import com.ucar.qtcassist.courseware.service.RemoteFileService;
import com.zuche.logger.client.util.HessianSerializerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.Executor;

/**
 * mq Listener
 *
 * @author shijie.xu
 * @since 2018年08月17日
 */
@Component
public class MesListener implements MessageListener {

    private static final String LOCATION = "qtcassist/tempFile";
    private static Logger LOGGER = LoggerFactory.getLogger(BaseCoursewareServiceImpl.class);
    @Autowired
    FileService fileService;
    @Autowired
    RemoteFileService remoteFileService;

    @Override
    public void recieveMessages(Message message) {
        Object obj=  HessianSerializerUtils.deserialize(message.getData());
        if (obj instanceof  FileDTO){
            FileDTO fileDTO = (FileDTO) obj;
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

    @Override
    public Executor getExecutor(){
        return  null;
    }
}
