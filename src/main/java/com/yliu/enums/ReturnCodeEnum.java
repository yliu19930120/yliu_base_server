package com.yliu.enums;

public enum ReturnCodeEnum {
	
	SUCCESS(0,"成功"),
	UNKNOWN_ABNORMAL(-1,"失败,未知异常"),
	ACCOUNT_OR_PASSWORD_ERROR(-2,"用户名或密码不正确"),
	ACCOUNT_EXISTS(-3,"用户已存在"),
	PARAMETERS_NOT_SPECIFIED(-4,"接口返回参数不规范");
	
	private int code;
	private String msg;
	private ReturnCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
