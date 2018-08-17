package com.ucar.qtcassist.courseware.service;

import com.taobao.metamorphosis.client.producer.SendResult;
import com.ucar.qtcassist.courseware.model.DTO.FileDTO;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月17日
 */
public interface MqService {
    SendResult msgSend(FileDTO object) throws InterruptedException;
}
