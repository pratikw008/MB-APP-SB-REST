package com.mobile.app.ws.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(InvalidUserIdException.class)
	public ResponseEntity<ApiError> handleInvalidUserIdException(InvalidUserIdException ex,WebRequest webRequest) {
		ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getClass().getSimpleName(), ex.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<ApiError>(apiError, HttpStatus.NOT_FOUND);
	}
}
