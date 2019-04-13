package com.yliu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yliu.bean.User;
import com.yliu.dao.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public User save(User user) {
		
		return repository.save(user);
	}

}
