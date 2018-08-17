package com.ucar.qtcassist.courseware.util;

import com.taobao.metamorphosis.client.extension.spring.DefaultMessageListener;
import com.taobao.metamorphosis.client.extension.spring.MetaqMessage;
import com.ucar.qtcassist.courseware.model.DTO.FileDTO;

import com.ucar.qtcassist.courseware.service.FileService;
import com.ucar.qtcassist.courseware.service.RemoteFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月17日
 */
@Component
public class MesListener extends DefaultMessageListener<FileDTO> {

    @Autowired
    FileService fileService;

    @Autowired
    RemoteFileService remoteFileService;

    @Override
    public void onReceiveMessages(MetaqMessage<FileDTO> metaqMessage) {
        FileDTO fileDTO = metaqMessage.getBody();
        if(fileService.typeCheck(fileDTO.getFile())){

        }
    }
}
