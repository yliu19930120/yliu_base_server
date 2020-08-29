package com.yliu.bean;

import com.yliu.enums.ReturnCodeEnum;

/**
 * 统一的响应实体
 * @author YLiu
 * @Email yliu19930120@163.com
 * 2019年4月15日 下午8:59:48
 */
public class Result<T> extends BaseResult{
	
	private T data;
	
	public Result() {
		super();
	}
	
	public Result(String message, int code, T data) {
		super();
		super.message = message;
		super.code = code;
		this.data = data;
	}
	
	public Result(T data) {
		super();
		this.data = data;
	}
	
	public Result(String message, int code) {
		super();
		super.message = message;
		super.code = code;
	}

	
	public Result(ReturnCodeEnum returnEnum) {
		super();
		super.message = returnEnum.getMsg();
		super.code = returnEnum.getCode();
	}
	
	public static<T> Result ok(T data){
		return new Result(data);
	}

	public static Result ok(String content){
		Result result = ok();
		result.setData(content);
		return result;
	}
	public static Result ok(){
		return new Result(ReturnCodeEnum.SUCCESS);
	}
	public static Result failue(ReturnCodeEnum returnEnum){
		return new Result(returnEnum);
	}
	public static Result failue(ReturnCodeEnum returnEnum,String errorMsg){
		String msg = String.format("%s: %s",returnEnum.getMsg(),errorMsg);
		return new Result(msg,returnEnum.getCode());
	}
	public static Result failue(){
		return new Result(ReturnCodeEnum.UNKNOWN_ABNORMAL);
	}

	public static Result failue(String msg){
		return new Result(msg,ReturnCodeEnum.UNKNOWN_ABNORMAL.getCode());
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		super.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		super.code = code;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}
