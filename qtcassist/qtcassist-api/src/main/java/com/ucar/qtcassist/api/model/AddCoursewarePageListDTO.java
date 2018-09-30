package com.ucar.qtcassist.api.model;

import java.util.List;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年09月28日
 */
public class AddCoursewarePageListDTO {
    /**
     * 课件列表
     * */
    private List<AddCoursewareListDTO> addCoursewareListDTO;
    /**
     * 总条数
     * */
    private Long count;

    public List<AddCoursewareListDTO> getAddCoursewareListDTO() {
        return addCoursewareListDTO;
    }

    public void setAddCoursewareListDTO(List<AddCoursewareListDTO> addCoursewareListDTO) {
        this.addCoursewareListDTO = addCoursewareListDTO;
    }



    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "AddCoursewarePageListDTO{" + "addCoursewareListDTO=" + addCoursewareListDTO + ", count=" + count + '}';
    }
}
