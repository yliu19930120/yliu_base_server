package com.yliu.utils;

import com.yliu.bean.User;

public class IdentityUtils {

    private static final ThreadLocal<User>  userThreadLocal = new ThreadLocal<>();

    public static void set(User user){
        userThreadLocal.set(user);
    }

    public static User get(){
        return userThreadLocal.get();
    }

    public static String userId(){
        if(userThreadLocal.get()!=null){
            return userThreadLocal.get().getId();
        }else {
            return null;
        }
    }
}
