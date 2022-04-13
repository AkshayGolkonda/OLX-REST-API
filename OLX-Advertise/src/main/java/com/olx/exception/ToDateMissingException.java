package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ToDateMissingException extends RuntimeException {
	
	private String message;

	public ToDateMissingException(String message) {
		this.message = message;
	}

	public ToDateMissingException() {
	}

	@Override
	public String toString() {
		return "Invalid To Date Exception: "+this.message;
	}
	
	

}

