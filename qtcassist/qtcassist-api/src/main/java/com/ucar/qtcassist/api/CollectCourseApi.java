package com.ucar.qtcassist.api;

import com.ucar.qtcassist.api.model.Query;
import com.ucar.qtcassist.api.model.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CollectCourseApi {

    @PostMapping("/getCollectCourse")
    ResponseResult getCollectCourse(@RequestBody Query query);

    @GetMapping("/addCollectCourse/{userId}/{courseId}")
    ResponseResult addCollectCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId);

    @PostMapping("/deleteCollectCourse")
    public ResponseResult deleteCollectCourse(@RequestBody Query query);
}
