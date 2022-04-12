package com.olx.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value=InvalidCredentialsException.class)
	public ResponseEntity<Object> handleCredentialConflict(RuntimeException exception,WebRequest request){
		String errorMessage = "{\"error\": \"Invalid Credentials id\"}";
		ResponseEntity<Object> response=
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	
	@ExceptionHandler(value=InvalidAuthTokenException.class)
	public ResponseEntity<Object> handleAuthTokenConflict(RuntimeException exception,WebRequest request){
		String errorMessage = "{\"error\": \"Invalid Auth Token id\"}";
		ResponseEntity<Object> response=
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
}
