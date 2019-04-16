package com.yliu.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.yliu.bean.User;

public interface UserService {
	
	User save(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException;
}
