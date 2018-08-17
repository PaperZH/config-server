package com.ucar.qtcassist.courseware.service.Impl;

import com.taobao.metamorphosis.client.extension.spring.MessageBuilder;
import com.taobao.metamorphosis.client.extension.spring.MetaqTemplate;
import com.taobao.metamorphosis.client.producer.SendResult;
import com.ucar.qtcassist.courseware.model.DTO.FileDTO;
import com.ucar.qtcassist.courseware.service.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月17日
 */
@Service
public class MqServiceImpl implements MqService {
    @Autowired
    MetaqTemplate template;

    @Override
    public SendResult msgSend(FileDTO object) throws InterruptedException {
        final String topic = "ucarabs_emp_update_topic";

        final SendResult sendResult = template.send(MessageBuilder.withTopic(topic).withBody(object));

        return sendResult;
    }
}
