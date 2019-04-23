package com.wirecard.challenge.services.exceptions;

public class PaymentNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -8488526012103872333L;
	
	public PaymentNotFoundException() {	}
	
	public PaymentNotFoundException(String message) {
		super(message);
	}
	
	public PaymentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
