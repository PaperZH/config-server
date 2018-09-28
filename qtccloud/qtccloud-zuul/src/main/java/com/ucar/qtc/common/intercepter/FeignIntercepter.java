package com.ucar.qtc.common.intercepter;

import com.ucar.qtc.common.constants.CommonConstants;
import com.ucar.qtc.common.context.FilterContextHandler;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author: cong.zhou01
 * @description: rpc接口调用拦截器
 * @date: 2018/8/7 10:19
 */
@Configuration
public class FeignIntercepter implements RequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    /**
     * 设置token
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = FilterContextHandler.getToken();
        logger.info("------feign设置token: "+token);
        requestTemplate.header(CommonConstants.CONTEXT_TOKEN, token);
    }
}