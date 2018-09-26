package com.ucar.qtc.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ERROR_TYPE;

/**
 * Description: TODO
 *
 * @author wangyang
 * @since 2018/9/20
 */

@Component
public class PrintErrorFilter extends ZuulFilter {

    /** 日志记录器 */
    private static final Logger LOGGER = LoggerFactory.getLogger(PrintErrorFilter.class);

    private static final String SEND_ERROR_FILTER_RAN = "sendErrorFilter.ran";

    @Override
    public String filterType() {
        return ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1000;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getThrowable() != null
                && !ctx.getBoolean(SEND_ERROR_FILTER_RAN, false);
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        LOGGER.error("路由异常",ctx.getThrowable());
        return null;
    }




}
