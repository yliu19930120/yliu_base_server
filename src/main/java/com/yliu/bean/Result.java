package com.yliu.bean;

import com.yliu.enums.ReturnCodeEnum;

/**
 * 统一的响应实体
 * @author YLiu
 * @Email yliu19930120@163.com
 * 2019年4月15日 下午8:59:48
 */
public class Result {
	
	private String message;
	private int code;
	private Object data;
	
	
	public Result() {
		super();
	}
	
	public Result(String message, int code, Object data) {
		super();
		this.message = message;
		this.code = code;
		this.data = data;
	}
	
	public Result(Object data) {
		super();
		this.data = data;
	}
	
	public Result(String message, int code) {
		super();
		this.message = message;
		this.code = code;
	}

	
	public Result(ReturnCodeEnum returnEnum) {
		super();
		this.message = returnEnum.getMsg();
		this.code = returnEnum.getCode();
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
