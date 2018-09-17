package com.ucar.qtcassist;

import com.taobao.metamorphosis.client.producer.SendResult;
import com.ucar.qtcassist.api.model.CoursewareDTO;
import com.ucar.qtcassist.courseware.dao.BaseCoursewareMapper;
import com.ucar.qtcassist.courseware.model.DO.BaseCoursewareDO;
import com.ucar.qtcassist.courseware.model.DTO.FileDTO;
import com.ucar.qtcassist.courseware.model.DTO.FrontCoursewareDTO;
import com.ucar.qtcassist.courseware.service.CoursewareService;
import com.ucar.qtcassist.courseware.service.MqService;
import com.ucar.qtcassist.courseware.service.RemoteFileService;
import com.ucar.qtcassist.courseware.util.GetBytes;
import com.zuche.framework.common.SpringApplicationContext;
import com.zuche.framework.enums.BusinessLineEnum;
import com.zuche.framework.udfs.client.UDFSClient;
import com.zuche.framework.udfs.client.upload.UDFSUploadResultVO;
import com.zuche.framework.udfs.client.upload.UDFSUploadVO;
import org.apache.commons.collections.CollectionUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=QtcassistCourseWareApplication.class)
public class QtcassistBaseCoursewareServiceDOControllerApplicationTests extends AbstractJUnit4SpringContextTests {

    @Autowired
    RemoteFileService remoteFileService;

    @Autowired
    CoursewareService coursewareService;

    @Autowired
    MqService mqService;

    @Autowired
    BaseCoursewareMapper baseCoursewareMapper;



    @Test
    public void upload() throws Exception {
        String url = null;
        File temfile = new File("D:\\img2.jpg");
        FileInputStream inputStream = null;
        inputStream = new FileInputStream(temfile);
        url = remoteFileService.uploadFile(inputStream, "test");
        System.out.println("++++++++++++++++" + url);
        Assert.assertNotNull(url);

    }

    @Test
    public void uploadtest() throws Exception {

        File file = new File("D:\\img2.jpg");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = GetBytes.getByteArrayInputStreamResource(inputStream);

        UDFSUploadVO vo = new UDFSUploadVO();
        vo.setName("123/456/7890.jpg");
        vo.setData(bytes);
        vo.setBusinessLine(BusinessLineEnum.EVP);

        UDFSUploadResultVO resultVO = UDFSClient.upload(vo);
        System.out.println(resultVO.getOriginalName());
        Assert.assertNotNull(resultVO);
    }


    @Test
    public void updateBaseCourseware() {
        BaseCoursewareDO baseCoursewareDO = new BaseCoursewareDO();
        baseCoursewareDO.setPreviewUrl("test123");
        baseCoursewareDO.setTypeId(1L);
        baseCoursewareDO.setCoursewareDescription("test123");
        baseCoursewareDO.setCoursewareName("test123");
        baseCoursewareDO.setPublishTime(new Date(System.currentTimeMillis()));
        baseCoursewareDO.setUpdateTime(new Date(System.currentTimeMillis()));
        baseCoursewareDO.setSourceUrl("test123");
        baseCoursewareDO.setId(4L);

        Assert.assertThat(baseCoursewareMapper.updateByPrimaryKeySelective(baseCoursewareDO),CoreMatchers.is(1));

    }

    @Test
    public void MqTest() throws Exception {
        String pdfLocation ="2018-09-10/convert/88.pdf";
        String pptLocation ="2018-09-10\\7ebb7c96a73d417d9315d70e44f95ffa.docx";
        File ppt = new File(pptLocation);
        File pdf = new File(pdfLocation);
        FileDTO fileDTO = new FileDTO();
        fileDTO.setFile(ppt);
        fileDTO.setId(1L);
        remoteFileService.fileConvert(ppt,pdf);
//        SendResult sendResult= mqService.msgSend(fileDTO);
//        Assert.assertTrue(sendResult.isSuccess());

    }

    @Test
    public void getList() {
        List<Long> temList = new ArrayList<>();
//        temList.add(1l);
//        temList.add(2l);
//        temList.add(3l);
//        temList.add(4l);
        List<CoursewareDTO> list = coursewareService.selectCoursewareList(temList);
        Assert.assertTrue(CollectionUtils.isNotEmpty(list));
        if(list != null) {
            for(int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).getSourceUrl());
            }
        } else {
            System.out.println("```````````````");
        }
    }

    @Test
    public void fiel(){
        File dir=new File("test.txt");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        Assert.assertNotNull(dir.getPath());
        System.out.println(dir.getPath());
    }

    @Before
    public void setUpBeforeClass() throws Exception {
        System.out.println("***********here init first***************");
        SpringApplicationContext.initApplicationContext(applicationContext);
    }

}
