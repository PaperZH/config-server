package com.ucar.qtc.admin.service;

import org.springframework.stereotype.Service;

/**
 * @author: cong.zhou01
 * @description: token服务
 * @date: 2018/8/7 10:19
 */
@Service
public interface TokenService {
    /**
     * 根据用户id生成token持久化
     */
    String createToken(Long userId);

    Long getUserIdByToken(String token);

    boolean removeToken(String token);
}
