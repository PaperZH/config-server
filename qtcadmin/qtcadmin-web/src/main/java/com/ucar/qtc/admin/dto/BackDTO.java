package com.ucar.qtc.admin.dto;

import java.util.List;

/**
 * @program: train
 * @description: 后台管理界面、课件查询封装DTO
 * @author: guodong.zhang01
 * @create: 2018-09-19 14:06
 **/
public class BackDTO {
    private List<BackCoursewareDTO> backCoursewareDTOList;
    private Long count;

    public List<BackCoursewareDTO> getBackCoursewareDTOList() {
        return backCoursewareDTOList;
    }

    public void setBackCoursewareDTOList(List<BackCoursewareDTO> backCoursewareDTOList) {
        this.backCoursewareDTOList = backCoursewareDTOList;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
