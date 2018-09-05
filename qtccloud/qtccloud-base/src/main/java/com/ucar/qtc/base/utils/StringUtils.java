package com.ucar.qtc.base.utils;

import java.util.UUID;

/**
 * @author: cong.zhou01
 * @description: 自定义字符串工具类
 * @date: 2018/8/7 10:19
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{

    public static String generateUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
