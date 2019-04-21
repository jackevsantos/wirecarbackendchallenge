package com.wirecard.challenge.util;

public enum TypePayment {
	CREDIT_CARD("CREDIT_CARD"), BOLETO("BOLETO");
	
	private TypePayment() {	}

	private TypePayment(String description) {
		this.description = description;
	}
	
	private String description;
	
	public String getDescription() {
		return description;
	}

}
