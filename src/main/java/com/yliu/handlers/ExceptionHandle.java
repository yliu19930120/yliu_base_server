package com.yliu.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yliu.bean.Result;
/**
 * 全局异常捕获
 * @author YLiu
 * @Email yliu19930120@163.com
 * 2019年4月15日 下午11:45:09
 */
@ControllerAdvice
public class ExceptionHandle {
	
	private final static Logger log = LoggerFactory.getLogger(ExceptionHandle.class);
	/**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result errorHandler(Exception ex) {
    	log.error("异常,e={}",ex);
        return new Result(ex.getMessage(),-1);
    }
}
