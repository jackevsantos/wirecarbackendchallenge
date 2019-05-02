package com.wirecard.challenge.services.exceptions;

public class ExistingCardException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ExistingCardException() {	}
	
	public ExistingCardException(String message) {
		super(message);
	}
	
	public ExistingCardException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
