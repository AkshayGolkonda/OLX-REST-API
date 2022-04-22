package com.olx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	@PostMapping(value="/user/authenticate", consumes={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> authenticate(@RequestBody User user) {
		return new ResponseEntity<String>(loginService.authenticate(user),HttpStatus.OK);
	}
	
	//2
	@DeleteMapping(value="/user/logout")
	public ResponseEntity<Boolean> logout(@RequestHeader("Authorization") String authToken) {
		return new ResponseEntity<Boolean>(loginService.logout(authToken),HttpStatus.OK);
	}
	
	//3
	@PostMapping(value="/user",consumes={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<User> addUser(@RequestBody User user) {
		return new ResponseEntity<User>(loginService.addUser(user),HttpStatus.OK);
	}
	
	//4
	@GetMapping(value="/user",produces={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<User> getUser(@RequestHeader("Authorization") String authToken) {
		return new ResponseEntity<User>(loginService.getUser(authToken),HttpStatus.OK);
	}
	
	//5
	@GetMapping(value="/token/validate",produces={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Boolean> validateJWT(@RequestHeader("Authorization")String authToken) {
		return new ResponseEntity<Boolean>(loginService.validateJWT(authToken), HttpStatus.OK);
	}
	
	
}
