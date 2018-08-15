package com.ucar.qtcassist;

import com.ucar.qtcassist.courseware.service.RemoteFileService;
import com.zuche.framework.common.SpringApplicationContext;
import com.zuche.framework.enums.BusinessLineEnum;
import com.zuche.framework.udfs.client.UDFSClient;
import com.zuche.framework.udfs.client.upload.UDFSUploadResultVO;
import com.zuche.framework.udfs.client.upload.UDFSUploadVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QtcassistBaseCoursewareServiceDOControllerApplicationTests extends AbstractJUnit4SpringContextTests {

    @Autowired
    RemoteFileService remoteFileService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void upload() throws Exception {
        String url = null;
        File temfile = new File("D:\\img2.jpg");
        FileInputStream inputStream = null;
        inputStream = new FileInputStream(temfile);
        url = remoteFileService.uploadFile(inputStream, "test");
        System.out.println("++++++++++++++++" + url);


    }

    @Test
    public void uploadtest() throws Exception {

        File file = new File("D:\\img2.jpg");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = getByteArrayInputStreamResource(inputStream);

        UDFSUploadVO vo = new UDFSUploadVO();
        vo.setName("123/456/7890.jpg");
        vo.setData(bytes);
        vo.setBusinessLine(BusinessLineEnum.EVP);

        UDFSUploadResultVO resultVO = UDFSClient.upload(vo);
        System.out.println(resultVO.getOriginalName());
    }


    private static byte[] getByteArrayInputStreamResource(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = 1024;
        byte tmp[] = new byte[len];
        int i;
        while((i = inputStream.read(tmp, 0, len)) > 0) {
            baos.write(tmp, 0, i);
        }
        byte[] data = baos.toByteArray();
        return data;
    }

    @Before
    public void setUpBeforeClass() throws Exception {
        System.out.println("***********here init first***************");
        SpringApplicationContext.initApplicationContext(applicationContext);
    }

}
