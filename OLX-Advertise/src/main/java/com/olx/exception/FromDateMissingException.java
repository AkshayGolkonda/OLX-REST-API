package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FromDateMissingException extends RuntimeException {
	
	private String message;

	public FromDateMissingException(String message) {
		this.message = message;
	}

	public FromDateMissingException() {
	}

	@Override
	public String toString() {
		return "Invalid From Date value Exception: "+this.message;
	}
	
	

}
