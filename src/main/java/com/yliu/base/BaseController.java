package com.yliu.base;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
	
	@Autowired
	private HttpSession httpSession;
	
	public void writeToSession(String name,Object value){
		httpSession.setAttribute(name, value);
	}
	
	public void removeFromSession(String name){
		httpSession.removeAttribute(name);
	}
}
