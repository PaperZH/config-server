package com.ucar.qtc.zuul.api;

import com.ucar.qtc.common.dto.MenuDTO;
import com.ucar.qtc.common.intercepter.FeignIntercepter;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-admin", configuration = FeignIntercepter.class)
public interface MenuService {
    @GetMapping("/menu/userMenus")
    List<MenuDTO> userMenus();
}
