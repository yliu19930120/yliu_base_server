package com.yliu.enums;

public enum ReturnCodeEnum {
	
	UNKNOWN_ABNORMAL(-1,"未知异常");
	
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
