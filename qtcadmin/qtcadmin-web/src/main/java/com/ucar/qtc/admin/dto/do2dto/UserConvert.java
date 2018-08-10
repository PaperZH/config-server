package com.ucar.qtc.admin.dto.do2dto;


import com.ucar.qtc.admin.domain.UserDO;
import com.ucar.qtc.admin.dto.UserDTO;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: cong.zhou01
 * @description: 用户DO和DTO映射
 * @date: 2018/8/7 10:19
 */
@org.mapstruct.Mapper
public interface UserConvert {
    UserConvert MAPPER = Mappers.getMapper(UserConvert.class);

    public UserDTO do2dto(UserDO person);

    public List<UserDTO> dos2dtos(List<UserDO> list);
}
