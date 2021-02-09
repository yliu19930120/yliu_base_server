package com.yliu.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import com.yliu.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yliu.bean.User;
import com.yliu.dao.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User save(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException
	,EntityExistsException{
		Optional<User> op = repository.findOneByAccount(user.getAccount());
		if(op.isPresent()){
			throw new EntityExistsException("用户已存在");
		}
		user.setPassword(PasswordUtils.encodeByMd5(user.getPassword()));
		User returnUser = repository.save(user);
		return returnUser;
	}

	public Optional<User> login(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Optional<User> op = repository.findOneByAccountAndPassword(
				user.getAccount(), PasswordUtils.encodeByMd5(user.getPassword()));
		return op;
	}

	public Optional<User> findOneById(String id) {
		return repository.findOneById(id);
	}

}
