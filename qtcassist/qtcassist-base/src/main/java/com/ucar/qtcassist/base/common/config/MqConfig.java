package com.ucar.qtcassist.base.common.config;

import com.taobao.metamorphosis.client.consumer.LoadBalanceStrategy;
import com.taobao.metamorphosis.client.extension.spring.DefaultMessageListener;
import com.taobao.metamorphosis.client.extension.spring.JavaSerializationMessageBodyConverter;
import com.taobao.metamorphosis.client.extension.spring.MessageListenerContainer;
import com.taobao.metamorphosis.client.extension.spring.MetaqMessageSessionFactoryBean;
import com.taobao.metamorphosis.client.extension.spring.MetaqTemplate;
import com.taobao.metamorphosis.client.extension.spring.MetaqTopic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: Mq相关的bean
 *
 * @author wangyang
 * @since 2018/8/16
 */

@Configuration
public class MqConfig {

    @Bean(name="sessionFactory")
    public MetaqMessageSessionFactoryBean sessionFactory () {
        MetaqMessageSessionFactoryBean metaqMessageSessionFactoryBean = new MetaqMessageSessionFactoryBean();
        metaqMessageSessionFactoryBean.setZkConnect("10.104.107.222:5181");
        metaqMessageSessionFactoryBean.setZkSessionTimeoutMs(30000);
        metaqMessageSessionFactoryBean.setZkConnectionTimeoutMs(30000);
        metaqMessageSessionFactoryBean.setZkSyncTimeMs(5000);
        return  metaqMessageSessionFactoryBean;
    }

    @Bean(name="messageBodyConverter")
    public JavaSerializationMessageBodyConverter messageBodyConverter () {
        return  new JavaSerializationMessageBodyConverter();
    }

    @Bean(name="metaqTemplate")
    public MetaqTemplate metaqTemplate () throws Exception {
        MetaqTemplate metaqTemplate = new MetaqTemplate();
        metaqTemplate.setMessageBodyConverter(messageBodyConverter());
        metaqTemplate.setMessageSessionFactory(sessionFactory().getObject());
        metaqTemplate.setShareProducer(true);
        metaqTemplate.setDefaultTopic("ucarabs_emp_update_topic");
        return  metaqTemplate;
    }

    @Bean(name="defaultTopic")
    public MetaqTopic defaultTopic (){
        MetaqTopic metaqTopic = new MetaqTopic();
        metaqTopic.setGroup("qtcassist");
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
    @Bean(name="metaqTemplate")
    public MessageListenerContainer listenerContainer() throws Exception {
        MessageListenerContainer messageListenerContainer = new MessageListenerContainer();
        messageListenerContainer.setMessageSessionFactory(sessionFactory().getObject());
        messageListenerContainer.setMessageBodyConverter(messageBodyConverter());
        messageListenerContainer.setShareConsumer(true);
        messageListenerContainer.setDefaultTopic(defaultTopic());
        //messageListenerContainer.setDefaultMessageListener();
        return  messageListenerContainer;
    }
}
