package com.guilhermemartinsdeoliveira.app.handler;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler(value = { HttpMessageNotReadableException.class, MethodArgumentNotValidException.class,
			HttpRequestMethodNotSupportedException.class, IllegalArgumentException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> invalidArgumentsOrInvalidParametersException(Exception ex, WebRequest req) {
		String errorMessage = "Invalid argument in request.";
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, errorMessage, ex));
	}

	@ExceptionHandler(value = { NoHandlerFoundException.class, NoSuchElementException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> notFoundException(Exception ex, WebRequest req) {
		String errorMessage = "Resource not found.";
		return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, errorMessage, ex));
	}
}