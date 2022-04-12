package com.olx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.User;
import com.olx.service.LoginService;

@RestController
@RequestMapping("/olx-login")
@CrossOrigin(origins="*")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	//1
	@PostMapping(value="/user/authenticate", consumes=MediaType.APPLICATION_JSON_VALUE)
	public String authenticate(@RequestBody User user) {
		return loginService.authenticate(user);
	}
	
	//2
	@DeleteMapping(value="/user/logout")
	public boolean logout(@RequestHeader("auth-token") String authToken) {
		return loginService.logout(authToken);
	}
	
	//3
	@PostMapping(value="/user",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public User addUser(@RequestBody User user) {
		return loginService.addUser(user);
	}
	
	//4
	@GetMapping(value="/user",produces=MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@RequestHeader("auth-token") String authToken) {
		return loginService.getUser(authToken);
	}
	
	//5
	@GetMapping(value="/token/validate",produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean validate(@RequestHeader("auth-token") String authToken) {
		return loginService.validate(authToken);
	}
	
	
}
