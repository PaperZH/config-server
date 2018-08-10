package com.ucar.qtc.zuul.controller;

import com.ucar.qtc.common.constants.CommonConstants;
import com.ucar.qtc.common.context.FilterContextHandler;
import com.ucar.qtc.common.dto.MenuDTO;
import com.ucar.qtc.zuul.prc.admin.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: cong.zhou01
 * @description: 测试工程
 * @date: 2018/8/7 10:19
 */
@RestController
public class LoginController {
    @Autowired
    MenuService menuService;

    @GetMapping({"/test"})
    List<MenuDTO> login(HttpServletRequest request)  {
        FilterContextHandler.setToken(request.getHeader(CommonConstants.CONTEXT_TOKEN));
        return menuService.userMenus();
    }
}
