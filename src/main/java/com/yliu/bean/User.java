package com.yliu.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

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
	@Column(length=11)
	private String phone;
	
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
	
	
}
