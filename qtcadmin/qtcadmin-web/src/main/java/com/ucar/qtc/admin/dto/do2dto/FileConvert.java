package com.ucar.qtc.admin.dto.do2dto;

import com.ucar.qtc.admin.domain.FileDO;
import com.ucar.qtc.admin.dto.FileDTO;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author cong.zhou01
 * @name FileConvert
 * @description TODO
 * @date 2018-08-16 10:02
 */
@org.mapstruct.Mapper
public interface FileConvert {
    FileConvert MAPPER = Mappers.getMapper(FileConvert.class);

    public FileDTO do2dto(FileDO person);

    public List<FileDTO> dos2dtos(List<FileDO> list);
}