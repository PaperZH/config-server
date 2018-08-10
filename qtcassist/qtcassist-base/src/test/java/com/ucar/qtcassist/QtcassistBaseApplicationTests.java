package com.ucar.qtcassist;

import com.ucar.qtcassist.util.RedisStringDemo;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QtcassistBaseApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private RedisStringDemo redisStringDemo;

    @Test
    public void redisTest(){
        String value = "by wangy";
        redisStringDemo.setKey("test", value);
        String getValue = redisStringDemo.getValue("test");
        Assert.assertThat(getValue, CoreMatchers.is(value));
    }

}
