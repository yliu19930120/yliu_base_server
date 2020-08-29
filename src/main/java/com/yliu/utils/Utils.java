package com.yliu.utils;

import com.yliu.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class Utils {

    private final static Logger log = LoggerFactory.getLogger(Utils.class);

    public static <T> T copy(Object object,Class<T> clazz){
        T t = null;
        try {
            t = clazz.getConstructor().newInstance();
            BeanUtils.copyProperties(object,t);
        } catch (InstantiationException | IllegalAccessException |InvocationTargetException | NoSuchMethodException e) {
            log.error("属性复制出错",e);
        }
        return t;
    }
}
