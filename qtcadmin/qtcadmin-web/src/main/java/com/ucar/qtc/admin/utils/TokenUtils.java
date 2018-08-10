package com.ucar.qtc.admin.utils;

import com.ucar.qtc.admin.dao.TokenDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: cong.zhou01
 * @description: 获取token类
 * @date: 2018/8/7 10:19
 */
public class TokenUtils {
    @Autowired
    TokenDao tokenDao;
    String getCurrent(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

}
