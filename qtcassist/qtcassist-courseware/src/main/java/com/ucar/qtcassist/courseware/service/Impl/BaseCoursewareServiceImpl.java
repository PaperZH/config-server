package com.ucar.qtcassist.courseware.service.Impl;

import com.ucar.qtcassist.courseware.model.DO.BaseCoursewareDO;
import com.ucar.qtcassist.courseware.model.DO.CoursewareTypeDO;
import com.ucar.qtcassist.courseware.model.DTO.BaseCoursewareListDTO;
import com.ucar.qtcassist.courseware.dao.BaseCoursewareMapper;
import com.ucar.qtcassist.courseware.dao.CoursewareTypeMapper;
import com.ucar.qtcassist.courseware.service.BaseCoursewareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月13日
 */

@Service
public class BaseCoursewareServiceImpl implements BaseCoursewareService {
    private static Logger LOGGER = LoggerFactory.getLogger(BaseCoursewareServiceImpl.class);
    @Autowired
    private BaseCoursewareMapper baseCoursewareMapper;
    @Autowired
    private CoursewareTypeMapper coursewareTypeMapper;

    @Override
    public List<BaseCoursewareListDTO> getAllBaseCoursewares() {
        List<BaseCoursewareDO> listDO = baseCoursewareMapper.getAllBaseCoursewares();
        List<BaseCoursewareListDTO> listDTO = new ArrayList<>();
        List<CoursewareTypeDO> listType = coursewareTypeMapper.getAllTypes();
        for(int i = 0; i < listDO.size(); i++) {
            BaseCoursewareListDTO baseCoursewareListDTO = new BaseCoursewareListDTO();
            BaseCoursewareDO baseCoursewareDO = listDO.get(i);
            baseCoursewareListDTO.setId(baseCoursewareDO.getId());
            baseCoursewareListDTO.setCoursewareName(baseCoursewareDO.getCoursewareName());
            baseCoursewareListDTO.setTypeId(baseCoursewareDO.getTypeId());
            //从所有的类型对象中找到当前课件的typeName
            for(int j = 0; j < listType.size(); j++) {
                LOGGER.info(j + ".listType ID:" + listType.get(j).getId().toString());
                LOGGER.info(j + ".baseCoursewareDO ID:" + baseCoursewareDO.getTypeId().toString());
                if(listType.get(j).getId() == baseCoursewareDO.getTypeId()) {
                    baseCoursewareListDTO.setTypeName(listType.get(j).getTypeName());
                    break;
                } else {
                    baseCoursewareListDTO.setTypeName("未定义类型");
                }
            }
            listDTO.add(baseCoursewareListDTO);
        }
        return listDTO;
    }


}
