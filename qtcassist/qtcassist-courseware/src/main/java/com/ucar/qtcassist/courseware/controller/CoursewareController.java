package com.ucar.qtcassist.courseware.controller;

import com.ucar.qtcassist.courseware.model.DO.BaseCoursewareDO;
import com.ucar.qtcassist.courseware.model.DO.CoursewareDO;
import com.ucar.qtcassist.courseware.model.DTO.BaseCoursewareListDTO;
import com.ucar.qtcassist.courseware.model.DTO.FileDTO;
import com.ucar.qtcassist.courseware.model.DTO.UploadCoursewareDTO;
import com.ucar.qtcassist.courseware.service.BaseCoursewareService;
import com.ucar.qtcassist.courseware.service.CoursewareService;
import com.ucar.qtcassist.courseware.service.RemoteFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Date;
import java.util.List;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月13日
 */
@RestController
@RequestMapping("/course")
public class CoursewareController {
    private static Logger LOGGER = LoggerFactory.getLogger(CoursewareController.class);
    @Autowired
    BaseCoursewareService baseCoursewareService;
    @Autowired
    CoursewareService coursewareService;
    @Autowired
    RemoteFileService remoteFileService;

    /**
     * 系统库课件列表
     */
    @RequestMapping(value = "/getAllBaseCoursewares", method = RequestMethod.POST)
    public List<BaseCoursewareListDTO> getAllBaseCoursewares() {
        return baseCoursewareService.getAllBaseCoursewares();
    }

    /**
     * 从本地上传课件进行添加
     */
    @RequestMapping(value = "/addCoursewareFromLocal", method = RequestMethod.POST)
    public FileDTO addCoursewareFromSys(@RequestParam("file") MultipartFile file, @RequestParam UploadCoursewareDTO uploadCoursewareDTO, HttpServletRequest request) throws Exception {

        String coursewareName = file.getOriginalFilename();
        BaseCoursewareDO baseCoursewareDO = new BaseCoursewareDO();
        baseCoursewareDO.setCoursewareName(uploadCoursewareDTO.getCoursewareName());
        baseCoursewareDO.setCoursewareDescription(uploadCoursewareDTO.getCoursewareDescription());
        baseCoursewareDO.setPublishTime(uploadCoursewareDTO.getPublishTime());
        baseCoursewareDO.setSourceUrl(remoteFileService.uploadFile(file.getInputStream(), coursewareName));
        baseCoursewareDO.setTypeId(uploadCoursewareDTO.getTypeId());

        Long BaseCourseId = null;
        BaseCourseId = baseCoursewareService.addBaseCourseware(baseCoursewareDO);

        CoursewareDO coursewareDO = new CoursewareDO();
        coursewareDO.setPublishTime(uploadCoursewareDTO.getPublishTime());
        coursewareDO.setCoursewareName(uploadCoursewareDTO.getCoursewareName());
        coursewareDO.setCoursewareDescription(uploadCoursewareDTO.getCoursewareDescription());
        coursewareDO.setBaseCoursewareId(BaseCourseId);
        coursewareDO.setTypeId(uploadCoursewareDTO.getTypeId());
        coursewareDO.setUpdateTime(new Date(System.currentTimeMillis()));
        if(BaseCourseId != null) {
            coursewareService.addCourseware(coursewareDO);
        }

        File f = null;
        file.transferTo(f);
        FileDTO fileDTO = new FileDTO();
        fileDTO.setFile(f);
        fileDTO.setId(BaseCourseId);
        fileDTO.setOriginalFilename(coursewareName);

        return fileDTO;




        /*//将MultipartFile转换为File
        File f = null;
        if(file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            try {
                ins = file.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            f = new File(file.getOriginalFilename());
            DocumentConverUtil.inputStreamToFile(ins, f);
            //FileCopyUtils.copy(this.part.getInputStream(), new FileOutputStream(dest));
        }



        File del = new File(f.toURI());
        del.delete();*/

        /*DocumentConverUtil documentConverUtil =new DocumentConverUtil();
        try {
            InputStream inputStream=file.getInputStream();
            remoteFileService.uploadFile(inputStream,coursewareName);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
