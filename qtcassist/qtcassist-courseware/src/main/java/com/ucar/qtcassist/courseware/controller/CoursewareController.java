package com.ucar.qtcassist.courseware.controller;

import com.ucar.qtcassist.base.util.DocumentConverUtil;
import com.ucar.qtcassist.courseware.constant.FileType;
import com.ucar.qtcassist.courseware.model.DTO.BackCoursewareDTO;
import com.ucar.qtcassist.courseware.model.DTO.BaseCoursewareListDTO;
import com.ucar.qtcassist.courseware.service.BaseCoursewareService;
import com.ucar.qtcassist.courseware.service.RemoteFileService;
import jdk.internal.util.xml.impl.Input;
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
import java.io.IOException;
import java.io.InputStream;
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
    @Autowired
    BaseCoursewareService baseCoursewareService;

    @Autowired
    RemoteFileService remoteFileService;

    private static Logger LOGGER = LoggerFactory.getLogger(CoursewareController.class);
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
    public String addCoursewareFromSys(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        BackCoursewareDTO backCoursewareDTO = new BackCoursewareDTO();
        String coursewareName = file.getOriginalFilename();
        int point =  coursewareName.lastIndexOf(".");
        //名称
        String Name = coursewareName.substring(0,point);
        LOGGER.error(Name);
        //后缀
        String suffix = coursewareName.substring(point+1,coursewareName.length());
        LOGGER.error(suffix);
        //暂存转换的pdf文件
        String pdfLocation = "qtcassist/tempFile/"+Name+".pdf";
        LOGGER.error("pdfLocation:"+pdfLocation);

        //将MultipartFile转换为File
        File f = null;
        if(file.equals("")||file.getSize()<=0){
            file = null;
        }else{
            InputStream ins = null;
            try {
                ins = file.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            f=new File(file.getOriginalFilename());
            DocumentConverUtil.inputStreamToFile(ins, f);
        }

        //根据文件名后缀判断文件类型
        if(suffix.equals(FileType.DOC)||suffix.equals(FileType.DOCX)||suffix.equals(FileType.PPT)||suffix.equals(FileType.PPTX)){
            File pdfFile = new File(pdfLocation);
            DocumentConverUtil docToPdf = new DocumentConverUtil(f, pdfFile);
            docToPdf.run();

        }

        File del = new File(f.toURI());
        del.delete();


        /*DocumentConverUtil documentConverUtil =new DocumentConverUtil();
        try {
            InputStream inputStream=file.getInputStream();
            remoteFileService.uploadFile(inputStream,coursewareName);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return null;
    }
}
