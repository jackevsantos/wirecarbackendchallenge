package com.wirecard.challenge.services.exceptions;

public class BuyNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -8488526012103872333L;
	
	public BuyNotFoundException() {	}
	
	public BuyNotFoundException(String message) {
		super(message);
	}
	
	public BuyNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
