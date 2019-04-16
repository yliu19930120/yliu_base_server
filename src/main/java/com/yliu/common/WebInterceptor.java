package com.yliu.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yliu.utils.JsonUtils;

/**
 * 拦截器
 * @author YLiu
 * @Email yliu19930120@163.com
 * 2019年4月15日 下午9:07:20
 */
public class WebInterceptor implements HandlerInterceptor{

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("拦截器正常工作");
	}


	
}
