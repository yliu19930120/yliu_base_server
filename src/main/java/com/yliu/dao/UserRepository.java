package com.yliu.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yliu.bean.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	Optional<User> findOneByAccount(String account);
	
	Optional<User> findOneByAccountAndPassword(String account, String password);
	
}
