package com.ucar.qtcassist.base.common.intercepter;

import com.ucar.qtcassist.base.common.constants.CommonConstants;
import com.ucar.qtcassist.base.common.context.FilterContextHandler;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: cong.zhou01
 * @description: rpc接口调用拦截器
 * @date: 2018/8/7 10:19
 */
public class FeignIntercepter implements RequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = FilterContextHandler.getToken();
        logger.info("feign设置token: " + token);
        requestTemplate.header(CommonConstants.CONTEXT_TOKEN, token);
    }
}