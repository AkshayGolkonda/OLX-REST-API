package com.olx.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAdvertiseIdException extends RuntimeException {
	
	private String message;

	public InvalidAdvertiseIdException(String message) {
		this.message = message;
	}

	public InvalidAdvertiseIdException() {
	}

	@Override
	public String toString() {
		return "Invalid Advertise id Exception: "+this.message;
	}
	
	

}
