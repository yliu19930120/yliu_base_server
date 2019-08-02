package com.yliu.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import com.yliu.bean.User;

public interface UserService {
	
	User save(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException,EntityExistsException;
	
	Optional<User> login(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException;
}
