package com.wirecard.challenge.util;

public enum TypePayment {
	CREDIT_CARD, BOLETO;
	
	private String description;
	
	public String getDescription() {
		return description;
	}

}
