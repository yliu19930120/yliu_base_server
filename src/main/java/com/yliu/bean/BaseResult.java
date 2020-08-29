package com.yliu.bean;

import io.swagger.annotations.ApiModelProperty;

public class BaseResult {
    @ApiModelProperty("返回的信息")
    protected String message;
    @ApiModelProperty("错误代码,非200为错误")
    protected int code;

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
}
