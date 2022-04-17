package com.olx.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.SignatureException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value=InvalidCredentialsException.class)
	public ResponseEntity<Object> handleCredentialConflict(RuntimeException exception,WebRequest request){
		String errorMessage = "{\"error\": \"Invalid Credentials \"}";
		ResponseEntity<Object> response=
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	
	@ExceptionHandler(value=InvalidAuthTokenException.class)
	public ResponseEntity<Object> handleAuthTokenConflict(RuntimeException exception,WebRequest request){
		String errorMessage = "{\"error\": \"Invalid Auth Token \"}";
		ResponseEntity<Object> response=
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	
	@ExceptionHandler(value=SignatureException.class)
	public ResponseEntity<Object> handleSignatureExceptionConflict(RuntimeException exception,WebRequest request){
		String errorMessage = "{\"error\": \"Invalid Signature \"}";
		ResponseEntity<Object> response=
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	
	
	@ExceptionHandler(value=AlreadyLoggedOutException.class)
	public ResponseEntity<Object> handleAlreadyLoggedOutExceptionConflict(RuntimeException exception,WebRequest request){
		String errorMessage = "{\"error\": \"User Already Logged Out\"}";
		ResponseEntity<Object> response=
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	
	
}
