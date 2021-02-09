package com.yliu.bean;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document
public class User extends Bean{

	@NotEmpty(message="姓名不能为空！")
	private String name;
	@NotEmpty(message="密码不能为空！")
	private String password;
	@NotEmpty(message="用户名不能为空！")
	private String account;
	@NotEmpty(message="电话号码不能为空！")
	@Size(min=11,max=11,message="电话长度必须为11")
	private String phone;

	private String token;

	public User() {
	}

	public User(String id,String token) {
		this.token = token;
		super.setId(id);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
