package com.ucar.qtcassist;

import com.ucar.qtcassist.api.model.VO.AddCoursewareQueryVO;
import com.ucar.qtcassist.course.dao.CourseCoursewareMapper;
import com.ucar.qtcassist.course.service.CourseCoursewareService;
import com.ucar.qtcassist.courseware.dao.CoursewareMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.interfaces.PBEKey;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QtcassistCourseApplication.class)
public class QtcassistCourseApplicationTests extends AbstractJUnit4SpringContextTests {
    @Autowired
    CourseCoursewareService courseCoursewareService;

    @Autowired
    private CourseCoursewareMapper courseCoursewareMapper;

    @Autowired
    private CoursewareMapper coursewareMapper;

    @Test
    public void getAddCoursewareListByCourseIdTest(){
        AddCoursewareQueryVO addCoursewareQueryVO = new AddCoursewareQueryVO();
        addCoursewareQueryVO.setCourseId(1L);
        addCoursewareQueryVO.setPageNo(1);
        addCoursewareQueryVO.setPageSize(5);
        courseCoursewareService.getAddCoursewareList(addCoursewareQueryVO);

    }

    @Test
    public void selectByListTest(){
        List<Long> testList = new ArrayList<>();
        testList.add(33L);
        testList.add(2L);
        testList.add(1L);
        testList.add(35L);
        testList.add(2L);
        System.out.println(coursewareMapper.selectByListKey(testList));
    }


}
