package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyLoggedOutException extends RuntimeException {
	
	private String message;

	public AlreadyLoggedOutException(String message) {
		this.message = message;
	}

	public AlreadyLoggedOutException() {
	}

	@Override
	public String toString() {
		return "User Already Logged out: "+this.message;
	}
	
	

}