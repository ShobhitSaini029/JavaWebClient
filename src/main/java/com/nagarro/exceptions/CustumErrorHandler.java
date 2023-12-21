package com.nagarro.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustumErrorHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomError> handleError(){
		
		CustomError error = new CustomError();
		
		error.setMessage("Page not found");
		error.setCode(404);
		error.setTimestamp(LocalDateTime.now());
		
		
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
