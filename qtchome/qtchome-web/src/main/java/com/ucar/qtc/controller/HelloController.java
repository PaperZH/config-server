package com.ucar.qtc.controller;

import com.ucar.qtcassist.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: TODO
 *
 * @author wangyang
 * @since 2018/8/21
 */

@RestController
public class HelloController {

    @Autowired
    private HelloBackgroundService helloBackgroundService;

    @RequestMapping("/hello")
    public Map<String, Object> hello() {
        Map<String, Object> ret = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        String s1 = helloBackgroundService.hello("张三");
        sb.append(s1).append("\n");
        User user = null;
        try {
            user = helloBackgroundService.hello(URLEncoder.encode("李四", "UTF-8"), 30);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sb.append(user.toString()).append("\n");
        String s3 = helloBackgroundService.hello(new User("王五", 19));
        sb.append(s3).append("\n");
        ret.put("show", sb.toString());
        sb.append("test2");
        return ret;
    }
}

