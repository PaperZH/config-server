package com.ucar.qtcassist;

import com.ucar.qtcassist.courseware.model.DTO.FileDTO;
import com.ucar.qtcassist.courseware.model.DTO.FrontCoursewareDTO;
import com.ucar.qtcassist.courseware.service.CoursewareService;
import com.ucar.qtcassist.courseware.service.MqService;
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
import java.sql.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QtcassistBaseCoursewareServiceDOControllerApplicationTests extends AbstractJUnit4SpringContextTests {

    @Autowired
    RemoteFileService remoteFileService;

    @Autowired
    CoursewareService coursewareService;

    @Autowired
    MqService mqService;

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

    @Test
    public void addCoursewareTest() {
        FrontCoursewareDTO frontCoursewareDTO = new FrontCoursewareDTO();
        frontCoursewareDTO.setBaseCoursewareId(1L);
        frontCoursewareDTO.setCoursewareDescription("test");
        frontCoursewareDTO.setCoursewareName("test1");
        frontCoursewareDTO.setTypeId(1L);
        frontCoursewareDTO.setPublishTime(new Date(System.currentTimeMillis()));
        frontCoursewareDTO.setUpdateTime(new Date(System.currentTimeMillis()));
//        coursewareService.addCourseware(frontCoursewareDTO);
    }

    @Test
    public void MqTest() {
        File ppt = new File("C:\\Users\\xsj\\Desktop\\qtc\\test.pptx");
        File pdf = new File("C:\\Users\\xsj\\Desktop\\qtc\\MQtest.pptx");
        FileDTO fileDTO = new FileDTO();
        fileDTO.setFile(ppt);
        fileDTO.setId(1L);
        try {
            mqService.msgSend(fileDTO);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUpBeforeClass() throws Exception {
        System.out.println("***********here init first***************");
        SpringApplicationContext.initApplicationContext(applicationContext);
    }

}
