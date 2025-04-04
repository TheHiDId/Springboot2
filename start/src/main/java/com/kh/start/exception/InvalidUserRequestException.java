package com.kh.start.exception;

public class InvalidUserRequestException extends RuntimeException {
	
	public InvalidUserRequestException(String msg) {
		
		super(msg);
	}
}
