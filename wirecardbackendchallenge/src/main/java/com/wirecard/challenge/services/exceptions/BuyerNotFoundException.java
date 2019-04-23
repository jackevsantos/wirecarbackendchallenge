package com.wirecard.challenge.services.exceptions;

public class BuyerNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -8488526012103872333L;
	
	public BuyerNotFoundException() {	}
	
	public BuyerNotFoundException(String message) {
		super(message);
	}
	
	public BuyerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
