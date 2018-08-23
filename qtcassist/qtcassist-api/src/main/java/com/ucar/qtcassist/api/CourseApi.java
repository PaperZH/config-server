package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.model.Query;
import com.ucar.qtcassist.api.model.ResponseResult;
import org.springframework.web.bind.annotation.*;

public interface CourseApi {
//    @RequestMapping(value = "/getCourseList", method = RequestMethod.GET)
//    ResponseResult hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @PostMapping("/getCourseList")
    ResponseResult getCourseList(@RequestBody Query query);
}
