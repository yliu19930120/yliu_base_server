package com.yliu.enums;

public enum ReturnCodeEnum {

	SUCCESS(0,"成功"),
	UNKNOWN_ABNORMAL(-10,"失败,未知异常"),
	ACCOUNT_OR_PASSWORD_ERROR(-20,"用户名或密码不正确"),
	ACCOUNT_EXISTS(-30,"用户已存在"),
	ACCOUNT_NOT_EXISTS(-40,"用户不存在"),
	PARAMETERS_NOT_SPECIFIED(-50,"表单参数异常"),
	NOT_LOGIN(-60,"未登录"),
	TOKEN_INVALID(-70,"token无效"),
	;

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
