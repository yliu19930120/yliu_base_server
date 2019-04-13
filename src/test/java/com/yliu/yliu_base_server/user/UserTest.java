package com.yliu.yliu_base_server.user;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yliu.bean.User;
import com.yliu.dao.UserRepository;
import com.yliu.utils.JsonUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	
	@Autowired
	private UserRepository repository;
	
	@Test
	public void saveUser(){
		User user = new User();
		user.setName("张三");
		user.setAccount("soinbbzn");
		user.setPassword("123456");
		user.setPhone("17328700183");
		User returnUser = repository.insert(user);
		System.out.println(JsonUtils.toJson(user));
	}
	
//	@Test
	public void selectUser(){
		User user = repository.findById("5cb15f2cc5bf624b1c6a6563").get();
		System.out.println(JsonUtils.toJson(user));
	}
}
