package com.ucar.qtc.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.ucar.qtc.common.constants.CommonConstants;
import com.ucar.qtc.common.context.FilterContextHandler;
import com.ucar.qtc.common.dto.MenuDTO;
import com.ucar.qtc.common.dto.UserToken;
import com.ucar.qtc.common.utils.*;
import com.ucar.qtc.zuul.api.MenuService;
import com.ucar.qtc.zuul.service.RedisCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

/**
 * @author: cong.zhou01
 * @description: 网关服务登陆过滤器
 * @date: 2018/8/7 10:19
 */
public class AccessFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    @Autowired
    MenuService menuService;

    @Autowired
    RedisCacheService redisCacheService;

    private String ignorePath = "/api-admin/login,/api-home/,/api-admin/pages,/api-admin/homeLogin,/v2/api-docs";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10000;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info("Zuul run, req IP: "+IPUtils.getIpAddr(request));

        final String requestUri = request.getRequestURI();
        if (isStartWith(requestUri)) {
            return null;
        }
        String accessToken = request.getHeader(CommonConstants.CONTEXT_TOKEN);
        if(null == accessToken || accessToken == ""){
            accessToken = request.getParameter(CommonConstants.TOKEN);
        }
        if (null == accessToken) {
            setFailedRequest(ResponseResult.error401(), 200);
            return null;
        }

        UserToken userToken = null;
        try {
            userToken = JwtUtils.getInfoFromToken(accessToken);
            logger.info("用户信息: "+JSON.toJSONString(userToken));
        } catch (Exception e) {
            logger.error("token校验出错: "+JSON.toJSONString(userToken));
            setFailedRequest(ResponseResult.error401(), 200);
            return null;
        }

        String redisToken = redisCacheService.getString(userToken.getUsername());

        if (StringUtils.isBlank(redisToken)) {
            logger.info("登陆token已经失效: "+JSON.toJSONString(userToken));
            setFailedRequest(ResponseResult.error401(), 200);
            return null;
        }

        if (!StringUtils.equals(redisToken, accessToken) &&
                !StringUtils.equals(userToken.getUserId(),"1")) {
            logger.info("用户已经重新登陆: "+JSON.toJSONString(userToken));
            setFailedRequest(ResponseResult.error401(), 200);
            return null;
        }

        FilterContextHandler.setToken(accessToken);
        if(!havePermission(request)){
            setFailedRequest(ResponseResult.error403(), 200);
            return null;
        }

        //移除忽略token
        Set<String> headers = (Set<String>) ctx.get("ignoredHeaders");
        headers.remove("authorization");
        return null;
    }

    private void setFailedRequest(Object body, int code) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        HttpServletResponse response = ctx.getResponse();
        PrintWriter out = null;
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            out = response.getWriter();
            out.write(JSONUtils.beanToJson(body));
            out.flush();
        }catch(IOException e){
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
        ctx.setSendZuulResponse(false);
    }

    private boolean havePermission(HttpServletRequest request){
        String currentURL = request.getRequestURI();
        List<MenuDTO> menuDTOS = menuService.userMenus();
        for(MenuDTO menuDTO:menuDTOS){
            if(currentURL!=null&&null!=menuDTO.getUrl()&&currentURL.startsWith(menuDTO.getUrl())){
                return true;
            }
        }
        return false;
    }

    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : ignorePath.split(",")) {

            if (requestUri.contains(s)) {
                return true;
            }
        }
        return flag;
    }
}
