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

	@ExceptionHandler(value=FromDateMissionException.class)
	public ResponseEntity<Object> handleFromDateConflict(RuntimeException exception,WebRequest request){
		String errorMessage = "{\"error\": \"Invalid FromDate \"}";
		ResponseEntity<Object> response=
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	
	@ExceptionHandler(value=InvalidAdvertiseIdException.class)
	public ResponseEntity<Object> handleAdvertiseIdConflict(RuntimeException exception,WebRequest request){
		String errorMessage = "{\"error\": \"Invalid Advertise Id \"}";
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
	
	@ExceptionHandler(value=InvalidCategoryIdException.class)
	public ResponseEntity<Object> handleCategoryIdConflict(RuntimeException exception,WebRequest request){
		String errorMessage = "{\"error\": \"Invalid Category Id \"}";
		ResponseEntity<Object> response=
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	
	@ExceptionHandler(value=InvalidPageIdException.class)
	public ResponseEntity<Object> handlePageIdConflict(RuntimeException exception,WebRequest request){
		String errorMessage = "{\"error\": \"Invalid Page Id \"}";
		ResponseEntity<Object> response=
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	
	@ExceptionHandler(value=InvalidStatusIdException.class)
	public ResponseEntity<Object> handleStatusIdConflict(RuntimeException exception,WebRequest request){
		String errorMessage = "{\"error\": \"Invalid Status Id \"}";
		ResponseEntity<Object> response=
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	
	@ExceptionHandler(value=OnDateMissingException.class)
	public ResponseEntity<Object> handleOnDateConflict(RuntimeException exception,WebRequest request){
		String errorMessage = "{\"error\": \"Invalid OnDate \"}";
		ResponseEntity<Object> response=
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	
	@ExceptionHandler(value=ToDateMissingException.class)
	public ResponseEntity<Object> handleToDateConflict(RuntimeException exception,WebRequest request){
		String errorMessage = "{\"error\": \"Invalid ToDate \"}";
		ResponseEntity<Object> response=
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
	
	@ExceptionHandler(value=UserNameDoesNotExistException.class)
	public ResponseEntity<Object> handleUserNameConflict(RuntimeException exception,WebRequest request){
		String errorMessage = "{\"error\": \"Username Does Not Exist \"}";
		ResponseEntity<Object> response=
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		return response;
	}
}
