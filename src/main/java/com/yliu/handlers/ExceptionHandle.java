package com.yliu.handlers;

import com.yliu.enums.ReturnCodeEnum;
import com.yliu.exception.LoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yliu.bean.Result;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局异常捕获
 * @author YLiu
 * @Email yliu19930120@163.com
 * 2019年4月15日 下午11:45:09
 */
@RestControllerAdvice
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

    @ExceptionHandler(LoginException.class)
    public Result handLoginException(LoginException ex){
        return Result.failue(ReturnCodeEnum.NOT_LOGIN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errorMsgs = new ArrayList<>();
        for (FieldError error:bindingResult.getFieldErrors()){
            errorMsgs.add(String.format("%s:%s",error.getField(),error.getDefaultMessage()));
        }
        return Result.failue(ReturnCodeEnum.PARAMETERS_NOT_SPECIFIED,errorMsgs.toString());
    }
}
