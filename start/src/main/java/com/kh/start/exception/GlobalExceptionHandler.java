package com.kh.start.exception;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private ResponseEntity<Map<String, String>> makeResponseEntity(RuntimeException e, HttpStatus status) {
		
		Map<String, String> error = new HashMap<>();
		
		error.put("cause", e.getMessage());
		
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(InvalidUserRequestException.class)
	public ResponseEntity<Map<String, String>> handleInvalidUserRequest(InvalidUserRequestException e) {
		
		return makeResponseEntity(e, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(MemberIdDuplicateException.class)
	public ResponseEntity<Map<String, String>> handleMemberIdDuplicate(MemberIdDuplicateException e) {
		
		return makeResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomAuthenticationException.class)
	public ResponseEntity<Map<String, String>> handleCustomAuthentication(CustomAuthenticationException e) {
		
		return makeResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleArgumentNotValid(MethodArgumentNotValidException e) {
		
		Map<String, String> errors = new HashMap<>();
		
		e.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		
		return ResponseEntity.badRequest().body(errors);
	}

}
