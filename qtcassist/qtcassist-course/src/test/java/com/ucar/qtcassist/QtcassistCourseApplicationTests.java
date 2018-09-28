package com.ucar.qtcassist;

import com.ucar.qtcassist.api.model.VO.AddCoursewareQueryVO;
import com.ucar.qtcassist.course.service.CourseCoursewareService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.interfaces.PBEKey;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QtcassistCourseApplication.class)
public class QtcassistCourseApplicationTests extends AbstractJUnit4SpringContextTests {
    @Autowired
    CourseCoursewareService courseCoursewareService;


    @Test
    public void getAddCoursewareListByCourseIdTest(){
        AddCoursewareQueryVO addCoursewareQueryVO = new AddCoursewareQueryVO();
        addCoursewareQueryVO.setCourseId(1L);
        addCoursewareQueryVO.setPageNo(1);
        addCoursewareQueryVO.setPageSize(5);
        courseCoursewareService.getAddCoursewareListByCourseId(addCoursewareQueryVO);

    }


}
