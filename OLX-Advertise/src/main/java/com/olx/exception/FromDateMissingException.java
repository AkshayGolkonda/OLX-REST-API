package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FromDateMissionException extends RuntimeException {
	
	private String message;

	public FromDateMissionException(String message) {
		this.message = message;
	}

	public FromDateMissionException() {
	}

	@Override
	public String toString() {
		return "Invalid From Date value Exception: "+this.message;
	}
	
	

}
