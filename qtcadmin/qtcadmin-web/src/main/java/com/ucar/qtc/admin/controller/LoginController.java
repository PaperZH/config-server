package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.UserDO;
import com.ucar.qtc.admin.service.MenuService;
import com.ucar.qtc.admin.service.TokenService;
import com.ucar.qtc.admin.service.UserService;
import com.ucar.qtc.admin.utils.MD5Utils;
import com.ucar.qtc.common.annotation.Log;
import com.ucar.qtc.common.context.FilterContextHandler;
import com.ucar.qtc.common.dto.LoginDTO;
import com.ucar.qtc.common.dto.UserToken;
import com.ucar.qtc.common.utils.JwtUtils;
import com.ucar.qtc.common.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: cong.zhou01
 * @description: 用户登陆退出
 * @date: 2018/8/7 10:19
 */
@RequestMapping()
@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    MenuService menuService;

    @Log("登录")
    @PostMapping("/login")
    ResponseResult login(@Valid @RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) {
        String username = loginDTO.getUsername().trim();
        String password = loginDTO.getPwd().trim();
        password = MD5Utils.encrypt(username, password);
        Map<String, Object> param = new HashMap<>();
        param.put("username", username);
        List<UserDO> userDOs = userService.list(param);
        if (userDOs.size() < 1) {
            return ResponseResult.error("用户或密码错误");
        }
        UserDO userDO = userDOs.get(0);
        if (null == userDO || !userDO.getPassword().equals(password)) {
            return ResponseResult.error("用户或密码错误");
        }
        UserToken userToken = new UserToken(userDO.getUsername(), userDO.getUserId().toString(), userDO.getName());
        String token = "";
        try {
            token = JwtUtils.generateToken(userToken, 2 * 60 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //首先清除用户缓存权限
        menuService.clearCache(userDO.getUserId());
        // String token = tokenService.createToken(userDO.getUserId());
        return ResponseResult.ok("登录成功")
                .put("token", token).put("user", userDO)
                .put("perms", menuService.PermsByUserId(userDO.getUserId()))
                .put("router", menuService.RouterDTOsByUserId(userDO.getUserId()));
    }


    @RequestMapping("/logout")
    ResponseResult logout(HttpServletRequest request, HttpServletResponse response) {
        menuService.clearCache(Long.parseLong(FilterContextHandler.getUserID()));
        return ResponseResult.ok();
    }


}
