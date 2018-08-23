package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.model.BaseCoursewareListDTO;
import com.ucar.qtcassist.api.model.Result;
import com.ucar.qtcassist.api.model.UploadCoursewareDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description: TODO
 *
 * @author
 * @since
 */
public interface CoursewareApi {

    @RequestMapping(value = "/getAllBaseCoursewares", method = RequestMethod.POST)
    Result<List<BaseCoursewareListDTO>> getAllBaseCoursewares();

    @RequestMapping(value = "/addCoursewareFromLocal", method = RequestMethod.POST)
    Result addCoursewareFromSys(MultipartFile file, UploadCoursewareDTO uploadCoursewareDTO, HttpServletRequest request) throws Exception;

    @RequestMapping(value = "/getBaseCourseware/{baseCoursewareId}", method = RequestMethod.GET)
    Result getBaseCourseware(@PathVariable Long baseCoursewareId);
}