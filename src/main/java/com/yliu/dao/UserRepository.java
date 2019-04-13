package com.yliu.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yliu.bean.User;

public interface UserRepository extends MongoRepository<User, String>{

}
