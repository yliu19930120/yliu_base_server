package com.yliu.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.yliu.bean.User;

public class TokenUtils {

    public static String getToken(User user){
        String token = null;
        token= JWT.create().withAudience(user.getId())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    public static String decodeToken(String token){
        return JWT.decode(token).getAudience().get(0);
    }
}
