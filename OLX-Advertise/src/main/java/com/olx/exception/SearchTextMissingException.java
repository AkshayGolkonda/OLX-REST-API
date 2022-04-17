package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SearchTextMissingException extends RuntimeException {
	
	private String message;

	public SearchTextMissingException(String message) {
		this.message = message;
	}

	public SearchTextMissingException() {
	}

	@Override
	public String toString() {
		return "Invalid To Date Exception: "+this.message;
	}
	
	

}
