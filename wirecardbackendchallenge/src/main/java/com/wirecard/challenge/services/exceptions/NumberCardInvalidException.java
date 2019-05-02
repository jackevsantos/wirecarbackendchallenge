package com.wirecard.challenge.services.exceptions;

public class NumberCardInvalidException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NumberCardInvalidException() {	}
	
	public NumberCardInvalidException(String message) {
		super(message);
	}
	
	public NumberCardInvalidException(String message, Throwable cause) {
		super(message, cause);
	}
}
