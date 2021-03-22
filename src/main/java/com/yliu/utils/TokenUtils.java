package com.yliu.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.yliu.bean.User;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

public class TokenUtils {

    private static final Long EXPIRATION_TIME = 1*1000*1000L;


    public static String getToken(User user){
        return JWT.create().withAudience(user.getId())
                .withExpiresAt(new Date(new Date().getTime()+EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(user.getPassword()));

    }

    public static String decodeToken(String token){
        List<String> audience = null;
        try {
            audience = JWT.decode(token).getAudience();
        } catch (JWTDecodeException e) {
            return null;
        }
        return CollectionUtils.isEmpty(audience) ?null:audience.get(0);
    }
}
