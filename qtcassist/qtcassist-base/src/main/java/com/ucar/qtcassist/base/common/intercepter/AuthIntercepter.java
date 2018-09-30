package com.ucar.qtcassist.base.common.intercepter;


import com.ucar.qtcassist.base.common.constant.CommonConstants;
import com.ucar.qtcassist.base.common.context.FilterContextHandler;
import com.ucar.qtcassist.base.common.dto.UserToken;
import com.ucar.qtcassist.base.util.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: cong.zhou01
 * @description: 登陆拦截器
 * @date: 2018/8/7 10:19
 */
public class AuthIntercepter extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(HandlerInterceptorAdapter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(CommonConstants.CONTEXT_TOKEN);
        UserToken userToken = JwtUtils.getInfoFromToken(token);
        FilterContextHandler.setToken(token);
        logger.info("拦截前台token: "+token);
        FilterContextHandler.setUsername(userToken.getUsername());
        FilterContextHandler.setUserID(userToken.getUserId());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        FilterContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
