package com.ucar.qtcassist.base.common.config;

import com.taobao.metamorphosis.client.MessageSessionFactory;
import com.taobao.metamorphosis.client.MetaClientConfig;
import com.taobao.metamorphosis.client.MetaMessageSessionFactory;
import com.taobao.metamorphosis.client.consumer.LoadBalanceStrategy;
import com.taobao.metamorphosis.client.consumer.MessageConsumer;
import com.taobao.metamorphosis.client.consumer.MessageListener;
import com.taobao.metamorphosis.client.extension.spring.DefaultMessageListener;
import com.taobao.metamorphosis.client.extension.spring.JavaSerializationMessageBodyConverter;
import com.taobao.metamorphosis.client.extension.spring.MessageListenerContainer;
import com.taobao.metamorphosis.client.extension.spring.MetaqMessageSessionFactoryBean;
import com.taobao.metamorphosis.client.extension.spring.MetaqTemplate;
import com.taobao.metamorphosis.client.extension.spring.MetaqTopic;
import com.taobao.metamorphosis.client.producer.MessageProducer;
import com.taobao.metamorphosis.utils.ZkUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: Mq相关的bean
 *
 * @author wangyang
 * @since 2018/8/16
 */

@Configuration
public class MqConfig {

    @Autowired(required=false)
    @Qualifier("mesListener")
    private MessageListener messageListener;

    @Value("${metaq.consumer.start-up}")
    boolean mataqConsumerStartUp;

    @Bean(name="sessionFactory")
    public MessageSessionFactory sessionFactory() throws Exception {
        final MetaClientConfig metaClientConfig = new MetaClientConfig();
        final ZkUtils.ZKConfig zkConfig = new ZkUtils.ZKConfig("10.104.107.222:5181",30000,30000,5000);
        metaClientConfig.setZkConfig(zkConfig);
        MessageSessionFactory sessionFactory = new MetaMessageSessionFactory(metaClientConfig);
        if (messageListener != null && mataqConsumerStartUp) {
            MessageConsumer consumer = sessionFactory.createConsumer(defaultTopic().getConsumerConfig());
            consumer.subscribe(defaultTopic().getTopic(), 1024 * 1024, messageListener);
            consumer.completeSubscribe();
        }

        return  sessionFactory;
    }

    @Bean(name="defaultProducer")
    public MessageProducer defaultProducer() throws Exception {
        MessageProducer messageProducer = sessionFactory().createProducer();
        messageProducer.publish(defaultTopic().getTopic());
        return messageProducer;
    }

    //@Bean(name="messageBodyConverter")
    //public JavaSerializationMessageBodyConverter messageBodyConverter () {
    //    return  new JavaSerializationMessageBodyConverter();
    //}
    //
    //@Bean(name="metaqTemplate")
    //public MetaqTemplate metaqTemplate () throws Exception {
    //    MetaqTemplate metaqTemplate = new MetaqTemplate();
    //    metaqTemplate.setMessageBodyConverter(messageBodyConverter());
    //    metaqTemplate.setMessageSessionFactory(sessionFactory());
    //    metaqTemplate.setShareProducer(true);
    //    metaqTemplate.setDefaultTopic("ucarabs_emp_update_topic");
    //    return  metaqTemplate;
    //}

    @Bean(name="defaultTopic")
    public MetaqTopic defaultTopic (){
        MetaqTopic metaqTopic = new MetaqTopic();
        metaqTopic.setGroup("ucar_abs");
        metaqTopic.setTopic("ucarabs_emp_update_topic");
        metaqTopic.setMaxBufferSize(1048576);
        metaqTopic.setFetchRunnerCount(Runtime.getRuntime().availableProcessors());
        metaqTopic.setMaxDelayFetchTimeInMills(5000);
        metaqTopic.setFetchTimeoutInMills(10000);
        metaqTopic.setCommitOffsetPeriodInMills(5000L);
        metaqTopic.setMaxFetchRetries(5);
        metaqTopic.setAlwaysConsumeFromMaxOffset(false);
        metaqTopic.setLoadBalanceStrategyType(LoadBalanceStrategy.Type.DEFAULT);
        metaqTopic.setMaxIncreaseFetchDataRetries(5);
        return  metaqTopic;
    }


    //@Bean(name="listenerContainer")
    //public MessageListenerContainer listenerContainer() throws Exception {
    //    MessageListenerContainer messageListenerContainer = new MessageListenerContainer();
    //    messageListenerContainer.setMessageSessionFactory(sessionFactory());
    //    messageListenerContainer.setMessageBodyConverter(messageBodyConverter());
    //    Map<MetaqTopic,  DefaultMessageListener> map = new HashMap<>();
    //    map.put(defaultTopic(), defaultMessageListener);
    //    return  messageListenerContainer;
    //}

}
