package com.ucar.qtc.admin.dto.do2dto;

import com.ucar.qtc.admin.domain.BannerDO;
import com.ucar.qtc.admin.domain.FileDO;
import com.ucar.qtc.admin.dto.BannerDTO;
import com.ucar.qtc.admin.dto.FileDTO;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 功能描述:
 *
 * @param: $param$
 * @return: $return$
 * @auther: $user$
 * @date: $date$ $time$
 */
@org.mapstruct.Mapper
public interface BannerConvert {
    BannerConvert MAPPER = Mappers.getMapper(BannerConvert.class);

    public BannerDTO do2dto(BannerDO banner);

    public List<BannerDTO> dos2dtos(List<BannerDO> list);
}