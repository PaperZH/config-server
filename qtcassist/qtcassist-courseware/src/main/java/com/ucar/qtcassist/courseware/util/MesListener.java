package com.ucar.qtcassist.courseware.util;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.consumer.MessageListener;
import com.ucar.qtcassist.courseware.constant.FileType;
import com.ucar.qtcassist.courseware.dao.BaseCoursewareMapper;
import com.ucar.qtcassist.courseware.model.DO.BaseCoursewareDO;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.concurrent.Executor;

/**
 * mq Listener
 *
 * @author shijie.xu
 * @since 2018年08月17日
 */
@Component
public class MesListener implements MessageListener {

    private static Logger LOGGER = LoggerFactory.getLogger(BaseCoursewareServiceImpl.class);
    @Autowired
    private FileService fileService;
    @Autowired
    private RemoteFileService remoteFileService;
    @Autowired
    private BaseCoursewareMapper baseCoursewareMapper;

    @Override
    public void recieveMessages(Message message)  {
        Object obj=  HessianSerializerUtils.deserialize(message.getData());
        FileDTO fileDTO = (FileDTO) obj;

        String location = fileDTO.getLocation() + "convert/";
        LOGGER.warn("location:"+location);
        File file = fileDTO.getFile();
        String coursewareName = fileDTO.getOriginalFilename();
        String preUrl=null;
        LOGGER.error("MqListener:"+coursewareName);

        int point = coursewareName.lastIndexOf(".");
        String Name = coursewareName.substring(0, point);
        File fPPT = file;
        File fPDF = null;
        if (obj instanceof  FileDTO){
            //判断是否为需要转化的文件类型
            if(fileService.typeCheck(coursewareName)) {

                String pdfLocation = location + Name + "." + FileType.PDF;
                //LOGGER.error("pdfLocation:" + pdfLocation);
                fPDF = new File(pdfLocation);
                File dir = new File(location);
                if(!dir.exists()) {
                    dir.mkdirs();
                }
                remoteFileService.fileConvert(fPPT, fPDF);
                LOGGER.error("conver successfully");
            }
            InputStream in = null;
            try {
                in = new FileInputStream(fPDF);
                preUrl=remoteFileService.uploadFile(in,Name + "." + FileType.PDF);
                LOGGER.error("upload successfully[preUrl]"+preUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //查BaseCourseware表，将preUrl放入表中
            BaseCoursewareDO baseCoursewareDO = baseCoursewareMapper.selectByPrimaryKey(fileDTO.getId());
            if(baseCoursewareDO!=null){
                baseCoursewareDO.setPreviewUrl(preUrl);
                baseCoursewareDO.setUpdateTime(new Date(System.currentTimeMillis()));
                int temp=baseCoursewareMapper.updateByPrimaryKeySelective(baseCoursewareDO);
                LOGGER.info("id:"+baseCoursewareDO.getId());
                if(temp==1){
                    LOGGER.error("add basecourseware successfully");
                }else {
                    LOGGER.error("add basecourseware failed");
                }
            }
        }
    }

    @Override
    public Executor getExecutor(){
        return  null;
    }
}
