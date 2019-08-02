package com.yliu.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yliu.base.BaseController;
import com.yliu.bean.Result;
import com.yliu.bean.User;
import com.yliu.enums.ReturnCodeEnum;
import com.yliu.service.UserService;

@RestController
@ResponseBody
@RequestMapping("/user")
public class UserController extends BaseController{
	
	private final static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public Result register(@Valid @RequestBody User user) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		log.info("用户{}注册",user.getAccount());
		try {
			userService.save(user);
		} catch (EntityExistsException e) {
			log.warn("用户{}已存在",user.getAccount());
			return Result.failue(ReturnCodeEnum.ACCOUNT_EXISTS);
		}
		return Result.ok();
	}
	
	@PostMapping("/login")
	public Result login(@RequestBody User user) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		Optional<User> op = userService.login(user);
		if(op.isPresent()){
			log.info("用户{}登录成功",user.getAccount());
			writeToSession(op.get().getId(), op.get());
			return Result.ok();
		}
		return Result.failue(ReturnCodeEnum.ACCOUNT_OR_PASSWORD_ERROR);
	}
}
