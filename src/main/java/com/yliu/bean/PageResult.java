package com.yliu.bean;

import com.yliu.enums.ReturnCodeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;

public class PageResult<T> extends BaseResult{

    private Page<T> data = new PageImpl<T>(Collections.emptyList());

    public PageResult(Page<T> data) {
        super.message = ReturnCodeEnum.SUCCESS.getMsg();
        super.code = ReturnCodeEnum.SUCCESS.getCode();
        this.data = data;
    }

    public Page<T> getData() {
        return data;
    }

    public void setData(Page<T> data) {
        this.data = data;
    }

    public static<T> PageResult<T> ok(Page<T> data){
        return new PageResult<T>(data);
    }
}
