package com.ucar.qtcassist.courseware.service.Impl;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.extension.spring.MetaqTopic;
import com.taobao.metamorphosis.client.producer.MessageProducer;
import com.taobao.metamorphosis.client.producer.SendResult;
import com.ucar.qtcassist.courseware.model.DTO.FileDTO;
import com.ucar.qtcassist.courseware.service.MqService;
import com.zuche.framework.remote.nio.codec.HessianSerializerUtils;
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
    private MessageProducer producer;

    @Autowired
    private MetaqTopic defaultTopic;

    @Override
    public SendResult msgSend(FileDTO fileDTO) throws Exception {
        String topic = defaultTopic.getTopic();

        producer.publish(topic);
        final SendResult sendResult = producer.sendMessage(new Message(topic, HessianSerializerUtils.serialize(fileDTO)));
        if (!sendResult.isSuccess())
        {
            System.err.println("Send message failed,error message:" + sendResult.getErrorMessage());
        }
        else {
            System.out.println("Send message successfully,sent to " + sendResult.getPartition());
        }

        return sendResult;
    }
}
