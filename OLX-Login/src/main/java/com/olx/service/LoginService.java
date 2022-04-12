package com.olx.service;

import com.olx.dto.User;

public interface LoginService {
	
	public String authenticate(User user);
	public boolean logout(String authToken);
	public User addUser(User user);
	public User getUser(String authtoken);
	public boolean validate(String authToken);
	
}
