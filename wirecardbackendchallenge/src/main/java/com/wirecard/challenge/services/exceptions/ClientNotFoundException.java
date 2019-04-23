package com.wirecard.challenge.services.exceptions;

public class ClientNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -8488526012103872333L;
	
	public ClientNotFoundException() {	}
	
	public ClientNotFoundException(String message) {
		super(message);
	}
	
	public ClientNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
