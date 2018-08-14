package com.ucar.qtcassist;

import com.ucar.qtcassist.util.RedisStringDemo;
import com.zuche.framework.common.SpringApplicationContext;
import com.zuche.framework.remote.RemoteClient;
import com.zuche.framework.remote.RemoteClientFactory;
import com.zuche.framework.remote.RemoteType;
import com.zuche.framework.upload.ResourceUploadResultVO;
import com.zuche.framework.upload.ResourceUploadScaleVO;
import com.zuche.framework.upload.ResourceUploadVO;
import com.zuche.framework.zk.utils.ZkUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QtcassistBaseApplicationTests extends AbstractJUnit4SpringContextTests {


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

    @Test
    public void uploadtest() throws Exception {
        RemoteClient client = RemoteClientFactory.getInstance(RemoteType.HESSIAN);

        File file = new File("D:\\img2.jpg");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = getByteArrayInputStreamResource(inputStream);

        ResourceUploadVO vo = new ResourceUploadVO();
        vo.setName("123/322/723.jpg");
        vo.setData(bytes);
        vo.setCompress(true);
        vo.setCompressSize(10);

        List<ResourceUploadScaleVO> list = new ArrayList<ResourceUploadScaleVO>();
        vo.setScaleInfos(list);
        ResourceUploadScaleVO scaleVO = new ResourceUploadScaleVO("small", 500, 500,null,true);
        list.add(scaleVO);

        ResourceUploadResultVO resultVO= (ResourceUploadResultVO)client.executeToObject("carresources.commonResourceInsert.service", vo);
        System.out.println(resultVO.getOriginalName());
    }

    private static byte[] getByteArrayInputStreamResource(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = 1024;
        byte tmp [] = new byte[len];
        int i ;
        while((i=inputStream.read(tmp, 0, len))>0){
            baos.write(tmp, 0, i);
        }
        byte[] data = baos.toByteArray();
        return data;
    }



    @Before
    public void setUpBeforeClass() throws Exception{
        System.out.println("***********here init first***************");
        SpringApplicationContext.initApplicationContext(applicationContext);
    }
}
