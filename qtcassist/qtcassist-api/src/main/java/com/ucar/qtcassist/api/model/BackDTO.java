package com.ucar.qtcassist.api.model;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 *后台管理界面、课件查询列表
 *
 * @author shijie.xu
 * @since 2018年09月19日
 */
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
