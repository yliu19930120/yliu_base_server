package com.yliu.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yliu.bean.User;
import com.yliu.dao.UserRepository;
import com.yliu.utils.PasswordUtils;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public User save(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		user.setPassword(PasswordUtils.encodeByMd5(user.getPassword()));
		return repository.save(user);
	}

}
