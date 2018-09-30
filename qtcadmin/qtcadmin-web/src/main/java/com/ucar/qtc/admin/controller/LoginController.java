package com.ucar.qtc.admin.controller;

import com.ucar.qtc.admin.domain.UserDO;
import com.ucar.qtc.admin.service.MenuService;
import com.ucar.qtc.admin.service.UserService;
import com.ucar.qtc.admin.utils.MD5Utils;
import com.ucar.qtc.common.annotation.Log;
import com.ucar.qtc.common.constants.CommonConstants;
import com.ucar.qtc.common.context.FilterContextHandler;
import com.ucar.qtc.common.dto.LoginDTO;
import com.ucar.qtc.common.dto.UserToken;
import com.ucar.qtc.common.service.RedisCacheService;
import com.ucar.qtc.common.utils.JwtUtils;
import com.ucar.qtc.common.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
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
@Api(description = "登陆退出服务API接口")
@RequestMapping()
@RestController
public class LoginController {

    @Value("${cache.time}")
    private String cacheTime;

    @Autowired
    UserService userService;

    @Autowired
    MenuService menuService;

    @Autowired
    RedisCacheService redisCacheService;

    /**
     * 用户登陆
     * @param loginDTO
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value="后台用户登陆", notes="后台用户登陆")
    @Log("登录")
    @PostMapping(value="/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseResult login(@Valid @RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) {
        String username = loginDTO.getUsername().trim();
        String password = loginDTO.getPassword().trim();
        password = MD5Utils.encrypt(username, password);
        Map<String, Object> param = new HashMap<>();
        param.put("username", username);
        param.put("password",password);
        List<UserDO> userDOs = userService.list(param);
        if (userDOs == null || userDOs.size() != 1) {
            return ResponseResult.error("用户或密码错误");
        }
        UserDO userDO = userDOs.get(0);
        UserToken userToken = new UserToken(userDO.getUsername(), userDO.getUserId().toString());
        String token = "";
        try {
            token = JwtUtils.generateToken(userToken, 2 * 60 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // redis缓存保存token
        redisCacheService.put(userDO.getUsername(),token, Long.parseLong(cacheTime)*60*1000);

        // redis缓存用户信息
        redisCacheService.put(CommonConstants.REDIS_USER_INFO_PREFIX+userDO.getUserId(),
                userDO);

        //首先清除用户缓存权限
        menuService.clearCache(userDO.getUserId());

        return ResponseResult.ok("登录成功")
                .put("token", token).put("user", userDO)
                .put("perms", menuService.BackPermsByUserId(userDO.getUserId()))
                .put("router", menuService.BackRouterDTOsByUserId(userDO.getUserId()));
    }

    /**
     * 用户登陆
     * @param loginDTO
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value="前台用户登陆", notes="前台用户登陆")
    @Log("登录")
    @PostMapping(value="/homeLogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseResult homeLogin(@Valid @RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) {
        String username = loginDTO.getUsername().trim();
        String password = loginDTO.getPassword().trim();
        password = MD5Utils.encrypt(username, password);
        Map<String, Object> param = new HashMap<>();
        param.put("username", username);
        param.put("password",password);
        List<UserDO> userDOs = userService.list(param);
        if (userDOs == null || userDOs.size() != 1) {
            return ResponseResult.error("用户或密码错误");
        }
        UserDO userDO = userDOs.get(0);
        UserToken userToken = new UserToken(userDO.getUsername(), userDO.getUserId().toString());
        String token = "";
        try {
            token = JwtUtils.generateToken(userToken, 2 * 60 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // redis缓存保存token
        redisCacheService.put(userDO.getUsername(),token, Long.parseLong(cacheTime)*60*1000);

        // redis缓存用户信息
        redisCacheService.put(CommonConstants.REDIS_USER_INFO_PREFIX+userDO.getUserId(),
                userDO);

        return ResponseResult.ok("登录成功")
                .put("token", token).put("user", userDO)
                .put("perms", menuService.FrontPermsByUserId(userDO.getUserId()))
                .put("router", menuService.FrontRouterDTOsByUserId(userDO.getUserId()));
    }


    /**
     * 用户退出
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value="用户退出", notes="用户退出")
    @Log("用户退出")
    @RequestMapping(value="/logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseResult logout(HttpServletRequest request, HttpServletResponse response) {
        menuService.clearCache(Long.parseLong(FilterContextHandler.getUserID()));
        redisCacheService.remove(FilterContextHandler.getUsername());
        redisCacheService.remove(CommonConstants.REDIS_USER_INFO_PREFIX+
                                FilterContextHandler.getUserID());
        return ResponseResult.ok();
    }
}
