package com.ucar.qtcassist.courseware.controller;

import com.ucar.qtcassist.api.model.*;
import com.ucar.qtcassist.courseware.model.DO.BaseCoursewareDO;
import com.ucar.qtcassist.courseware.model.DO.CoursewareDO;
import com.ucar.qtcassist.courseware.model.DTO.FileDTO;
import com.ucar.qtcassist.courseware.service.BaseCoursewareService;
import com.ucar.qtcassist.courseware.service.CoursewareService;
import com.ucar.qtcassist.courseware.service.MqService;
import com.ucar.qtcassist.courseware.service.RemoteFileService;
import com.ucar.qtcassist.api.CoursewareApi;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月13日
 */
@RestController
@RequestMapping("/course")
public class CoursewareController implements CoursewareApi {
    private static Logger LOGGER = LoggerFactory.getLogger(CoursewareController.class);
    @Autowired
    private BaseCoursewareService baseCoursewareService;
    @Autowired
    private CoursewareService coursewareService;
    @Autowired
    private RemoteFileService remoteFileService;
    @Autowired
    private MqService mqService;

    /**
     * 系统库课件列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getAllBaseCoursewares", method = RequestMethod.POST)
    public Result<List<BaseCoursewareListDTO>> getAllBaseCoursewares() {
        return Result.getSuccessResult(baseCoursewareService.getAllBaseCoursewares());
    }

    /**
     * 从本地上传课件进行添加
     *
     * @param file,uploadCoursewareDTO
     */
    @RequestMapping(value = "/addCoursewareFromLocal", method = RequestMethod.POST)
    public Result<MqBackCoursewareDTO> addCoursewareFromSys(MultipartFile file, UploadCoursewareDTO uploadCoursewareDTO, HttpServletRequest request) throws Exception {
        FileDTO fileDTO = new FileDTO();
        BaseCoursewareDO baseCoursewareDO = new BaseCoursewareDO();
        CoursewareDO coursewareDO = new CoursewareDO();
        MqBackCoursewareDTO mqBackCoursewareDTO = new MqBackCoursewareDTO();
        if(!file.isEmpty()) {
            String coursewareName = file.getOriginalFilename();
            String sourceUrl = remoteFileService.uploadFile(file.getInputStream(), coursewareName);

            baseCoursewareDO.setCoursewareName(uploadCoursewareDTO.getCoursewareName());
            baseCoursewareDO.setCoursewareDescription(uploadCoursewareDTO.getCoursewareDescription());
            baseCoursewareDO.setPublishTime(uploadCoursewareDTO.getPublishTime());
            baseCoursewareDO.setSourceUrl(sourceUrl);
            baseCoursewareDO.setTypeId(uploadCoursewareDTO.getTypeId());

            Long baseCourseId = null;
            if(baseCoursewareService.addBaseCourseware(baseCoursewareDO) == 1) {
                baseCourseId = baseCoursewareService.getNewId();
            }


            coursewareDO.setPublishTime(uploadCoursewareDTO.getPublishTime());
            coursewareDO.setCoursewareName(uploadCoursewareDTO.getCoursewareName());
            coursewareDO.setCoursewareDescription(uploadCoursewareDTO.getCoursewareDescription());
            coursewareDO.setBaseCoursewareId(baseCourseId);
            coursewareDO.setTypeId(uploadCoursewareDTO.getTypeId());
            coursewareDO.setUpdateTime(uploadCoursewareDTO.getPublishTime());

            if(baseCourseId != null) {
                coursewareService.addCourseware(coursewareDO);
            }

            mqBackCoursewareDTO.setSourceUrl(sourceUrl);
            mqBackCoursewareDTO.setPublishTime(uploadCoursewareDTO.getPublishTime());
            mqBackCoursewareDTO.setCoursewareDescription(uploadCoursewareDTO.getCoursewareDescription());
            mqBackCoursewareDTO.setCoursewareName(uploadCoursewareDTO.getCoursewareName());

            String filePath = request.getSession().getServletContext().getRealPath("/upload/") + "";
            LOGGER.info("fileLocation:" + filePath);
            File dir = new File(filePath);
            if(!dir.exists()) {
                dir.mkdir();
            }
            String path = filePath + file.getOriginalFilename();
            File tempFile = null;
            //save to the /upload path
            try {
                tempFile = new File(path);
                //copy MultipartFile into File
                FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);
                fileDTO.setFile(tempFile);
                fileDTO.setId(baseCourseId);
                fileDTO.setOriginalFilename(coursewareName);
                fileDTO.setLocation(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mqService.msgSend(fileDTO);
        }
        return Result.getSuccessResult(mqBackCoursewareDTO);
    }

    /**
     * 完成本地上传后获取课件详情
     */
    @RequestMapping(value = "/getBaseCourseware/{baseCoursewareId}", method = RequestMethod.GET)
    public Result<BaseCoursewareDTO> getBaseCourseware(@PathVariable Long baseCoursewareId) {

        return Result.getSuccessResult(baseCoursewareService.getBaseCourseware(baseCoursewareId));
    }

}



















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
