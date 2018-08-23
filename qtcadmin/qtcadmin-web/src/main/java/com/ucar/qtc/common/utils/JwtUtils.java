package com.ucar.qtc.common.utils;

import com.ucar.qtc.common.constants.CommonConstants;
import com.ucar.qtc.common.dto.UserToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author: cong.zhou01
 * @description: JWT工具类
 * @date: 2018/8/7 10:19
 */
public class JwtUtils {
    public static String generateToken(UserToken userToken, int expire) throws Exception {
        String token = Jwts.builder()
                .setSubject(userToken.getUsername())
                .claim(CommonConstants.CONTEXT_USER_ID, userToken.getUserId())
                .claim(CommonConstants.CONTEXT_NAME, userToken.getName())
                .claim(CommonConstants.CONTEXT_NICKNAME, userToken.getNickname())
                .claim(CommonConstants.CONTEXT_AVATAR, userToken.getAvatar())
                .claim(CommonConstants.RENEWAL_TIME,new Date(System.currentTimeMillis()+expire/2))
                .setExpiration(new Date(System.currentTimeMillis()+expire))
                .signWith(SignatureAlgorithm.HS256, CommonConstants.JWT_PRIVATE_KEY)
                .compact();
        return token;
    }


    public static UserToken getInfoFromToken(String token) throws Exception {
        Claims claims = Jwts.parser()
                .setSigningKey(CommonConstants.JWT_PRIVATE_KEY).parseClaimsJws(token)
                .getBody();
        UserToken adminUserToken = new UserToken();
        adminUserToken.setUsername(claims.getSubject());
        adminUserToken.setUserId(claims.get(CommonConstants.CONTEXT_USER_ID).toString());
        if (claims.get(CommonConstants.CONTEXT_NAME) != null) {
            adminUserToken.setName(claims.get(CommonConstants.CONTEXT_NAME).toString());
        }
        if (claims.get(CommonConstants.CONTEXT_NICKNAME) != null) {
            adminUserToken.setNickname(claims.get(CommonConstants.CONTEXT_NICKNAME).toString());
        }
        if (claims.get(CommonConstants.CONTEXT_AVATAR) != null) {
            adminUserToken.setAvatar(claims.get(CommonConstants.CONTEXT_AVATAR).toString());
        }
        return adminUserToken;
    }
}
