package com.guilhermemartinsdeoliveira.app.config.handlers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class ApiError {

	private HttpStatus status;
	private LocalDateTime timestamp;
	private String message;
	private List<ApiSubError> subErrors;

	private ApiError() {
		timestamp = LocalDateTime.now();
	}

	ApiError(HttpStatus status) {
		this();
		this.status = status;
	}

	ApiError(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
	}

	ApiError(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
	}
	
	abstract class ApiSubError {

	}

	@Data
	@EqualsAndHashCode(callSuper = false)
	@AllArgsConstructor
	class ApiValidationError extends ApiSubError {
	   private String object;
	   private String field;
	   private Object rejectedValue;
	   private String message;

	   ApiValidationError(String object, String message) {
	       this.object = object;
	       this.message = message;
	   }
	}
}
