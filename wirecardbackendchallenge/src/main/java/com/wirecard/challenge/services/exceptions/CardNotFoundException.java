package com.wirecard.challenge.services.exceptions;

public class CardNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -8488526012103872333L;
	
	public CardNotFoundException() {	}
	
	public CardNotFoundException(String message) {
		super(message);
	}
	
	public CardNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
