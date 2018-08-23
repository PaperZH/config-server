package com.ucar.qtcassist.courseware.service;

import com.taobao.metamorphosis.client.producer.SendResult;
import com.ucar.qtcassist.courseware.model.DTO.FileDTO;

/**
 * 消息队列服务
 *
 * @author shijie.xu
 * @since 2018年08月17日
 */
public interface MqService {
    /**
     * 消息发送
     *
     * @param fileDTO
     * @return
     */
    SendResult msgSend(FileDTO fileDTO) throws Exception;
}
