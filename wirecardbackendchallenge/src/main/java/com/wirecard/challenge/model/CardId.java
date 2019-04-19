package com.wirecard.challenge.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CardId implements Serializable{
	
	@Column(name = "HOLDERNAME", nullable = false)
	private String holderName;
	
	@Column(name = "NUMBERCARD", nullable = false)
	private String numberCard;
	
	@Column(name = "EXPIRATIONDATE", nullable = false)
	private Date expirationDate;
	
	@Column(name = "CVV", nullable = false)
	private String cvv;
	
	public CardId() {}
	
	public CardId(String holderName, String numberCard, Date expirationDate, String cvv) {
		this.holderName = holderName;
		this.numberCard = numberCard;
		this.expirationDate = expirationDate;
		this.cvv = cvv;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getNumberCard() {
		return numberCard;
	}

	public void setNumberCard(String numberCard) {
		this.numberCard = numberCard;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	
}
