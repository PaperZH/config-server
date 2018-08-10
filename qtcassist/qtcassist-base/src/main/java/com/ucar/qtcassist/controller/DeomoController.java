package com.ucar.qtcassist.controller;

import com.ucar.qtcassist.dao.DemoMapper;
import com.ucar.qtcassist.model.Demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;


/**
 * Description: TODO
 *
 * @author wangyang
 * @since 2018/8/6
 */

@RestController
public class DeomoController {

    private static final String template = "Hello, %s!";
    private static final AtomicLong counter = new AtomicLong();

    private static final Logger LOGGER =  LoggerFactory.getLogger(DeomoController.class);

    @Autowired
    private  DemoMapper demoMapper;

    @RequestMapping("/demo")
    public Demo greeting(@RequestParam(value="name", defaultValue="World") String name) {
        LOGGER.info("/demo Starting");
        Demo demo = new Demo();
        demo.setContent(String.format(template, name));
        demo.setId(counter.incrementAndGet());
        demoMapper.insert(demo);
        return demoMapper.selectByPrimaryKey(counter.get());
    }
}
