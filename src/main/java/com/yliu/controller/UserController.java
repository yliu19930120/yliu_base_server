package com.yliu.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import javax.persistence.EntityExistsException;
import javax.validation.Valid;

import com.yliu.annotation.Login;
import com.yliu.service.UserService;
import com.yliu.utils.TokenUtils;
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

@RestController
@ResponseBody
@RequestMapping("/user")
public class UserController extends BaseController{

	private final static Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Login(required = false)
	@PostMapping("/register")
	public Result register(@Valid @RequestBody User user) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		log.info("用户{}注册",user.getAccount());
		try {
			User reUser = userService.save(user);
			return Result.ok(new User(reUser.getId(),TokenUtils.getToken(reUser)));
		} catch (EntityExistsException e) {
			log.warn("用户{}已存在",user.getAccount());
			return Result.failue(ReturnCodeEnum.ACCOUNT_EXISTS);
		}
	}

	@Login(required = false)
	@PostMapping("/login")
	public Result login(@RequestBody User user) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		Optional<User> op = userService.login(user);
		if(op.isPresent()){
			log.info("用户{}登录成功",user.getAccount());
			return Result.ok(new User(op.get().getId(),TokenUtils.getToken(op.get())));
		}
		return Result.failue(ReturnCodeEnum.ACCOUNT_OR_PASSWORD_ERROR);
	}

	@Login(required = false)
	@PostMapping("/valid")
	public Result tokenValid(@RequestBody User user){
		String userId = TokenUtils.decodeToken(user.getToken());
		if(userId==null){
			return Result.failue(ReturnCodeEnum.TOKEN_INVALID);
		}
		Optional<User> op = userService.findOneById(userId);
		if(op.isPresent()){
			log.info("token有效",user.getId());
			return Result.ok(new User(op.get().getId(),TokenUtils.getToken(op.get())));
		}
		return Result.failue(ReturnCodeEnum.TOKEN_INVALID);
	}

}
