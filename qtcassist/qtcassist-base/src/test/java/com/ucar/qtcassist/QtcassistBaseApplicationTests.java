package com.ucar.qtcassist;

import com.ucar.qtcassist.base.util.RedisStringUtil;
import com.zuche.framework.common.SpringApplicationContext;
import com.zuche.framework.enums.BusinessLineEnum;
import com.zuche.framework.remote.RemoteClient;
import com.zuche.framework.remote.RemoteClientFactory;
import com.zuche.framework.remote.RemoteType;
import com.zuche.framework.udfs.client.UDFSClient;
import com.zuche.framework.udfs.client.upload.UDFSUploadResultVO;
import com.zuche.framework.udfs.client.upload.UDFSUploadVO;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QtcassistBaseApplicationTests extends AbstractJUnit4SpringContextTests {


    @Autowired
    private RedisStringUtil redisStringDemo;

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

        UDFSUploadVO vo = new UDFSUploadVO();
        vo.setName("123/456/7890.jpg");
        vo.setData(bytes);
        vo.setBusinessLine(BusinessLineEnum.EVP);

        UDFSUploadResultVO resultVO= (UDFSUploadResultVO)client.executeToObject("ucarudfs.commonResourceInsert.service", vo);
        Assert.assertNotNull(resultVO);
        System.out.println(resultVO.getOriginalName());
        Assert.assertThat(resultVO.getOriginalName(),CoreMatchers.notNullValue());
    }

    @Test
    public void uploadtest2() throws Exception {
        RemoteClient client = RemoteClientFactory.getInstance(RemoteType.HESSIAN);

        File file = new File("D:\\img2.jpg");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = getByteArrayInputStreamResource(inputStream);

        UDFSUploadVO vo = new UDFSUploadVO();
        vo.setName("123/456/7890.jpg");
        vo.setData(bytes);
        vo.setBusinessLine(BusinessLineEnum.EVP);

        UDFSUploadResultVO resultVO=  UDFSClient.upload(vo);
        Assert.assertNotNull(resultVO);
        System.out.println(resultVO.getOriginalName());
        Assert.assertThat(resultVO.getOriginalName(),CoreMatchers.notNullValue());


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
