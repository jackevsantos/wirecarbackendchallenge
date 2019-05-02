package com.wirecard.challenge.services.exceptions;

public class ExistingBuyerException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ExistingBuyerException() {	}
	
	public ExistingBuyerException(String message) {
		super(message);
	}
	
	public ExistingBuyerException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
