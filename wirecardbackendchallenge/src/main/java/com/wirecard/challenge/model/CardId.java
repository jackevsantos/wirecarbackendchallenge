package com.wirecard.challenge.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Embeddable
public class CardId implements Serializable{
	
	private static final long serialVersionUID = 7929087643220053323L;

	@Column(name = "HOLDERNAME", nullable = false)
	@NotNull(message = "You need to inform a holder name.")
	private String holderName;
	
	@Column(name = "NUMBERCARD", nullable = false)
	@NotNull(message = "You need to inform a number card.")
	private String numberCard;
	
	@Column(name = "EXPIRATIONDATE", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "You need to inform a expiration date.")
	private Date expirationDate;
	
	@Column(name = "CVV", nullable = false, length = 3)
	@NotNull(message = "You need to inform a cvv code.")
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
