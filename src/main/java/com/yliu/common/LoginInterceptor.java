package com.yliu.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yliu.annotation.Login;
import com.yliu.bean.User;
import com.yliu.constant.BaseConst;
import com.yliu.enums.ReturnCodeEnum;
import com.yliu.exception.LoginException;
import com.yliu.service.UserService;
import com.yliu.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * 登录拦截
 * @author YLiu
 * @Email yliu19930120@163.com
 * 2019年4月15日 下午9:07:20
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{

	private UserService userService;

	public LoginInterceptor(UserService userService) {
		this.userService = userService;
	}

	private final static Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean flag = loginInterceptor(request,response,handler);
		return flag;
	}

	private boolean loginInterceptor(HttpServletRequest request, HttpServletResponse response, Object handler) throws LoginException {
		if(!(handler instanceof HandlerMethod)){
			return true;
		}
		HandlerMethod handlerMethod=(HandlerMethod)handler;
		Method method=handlerMethod.getMethod();
		if (method.isAnnotationPresent(Login.class)) {
			Login passToken = method.getAnnotation(Login.class);
			if (!passToken.required()) {
				return true;
			}
		}

		String token = request.getHeader(BaseConst.TOKEN_KEY);

		if(StringUtils.isBlank(token)){
			throw new LoginException(ReturnCodeEnum.NOT_LOGIN.getMsg());
		}else {
			String userId = TokenUtils.decodeToken(token);
			Optional<User> user = userService.findOneById(userId);
			if(!user.isPresent()){
				throw new LoginException(ReturnCodeEnum.ACCOUNT_EXISTS.getMsg());
			}
		}

		return true;
	}
}
