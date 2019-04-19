package com.wirecard.challenge.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;

import com.wirecard.challenge.util.TypePayment;

@Entity
public class Payment {
	@Id
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "AMOUNT", nullable = false)
	private BigDecimal amount;
	
	@Column(name = "TYPE", nullable = false)
	private TypePayment type;
	
	@OneToOne
	@JoinColumns({
	  @JoinColumn(name = "HOLDERNAME"),
	  @JoinColumn(name = "NUMBERCARD"),
	  @JoinColumn(name = "EXPIRATIONDATE"),
	  @JoinColumn(name = "CVV"),
	})
	private Card card;
	
	@Column(name = "BOLETONUMBER", nullable = true)
	private String boletoNumber;
	
	@Column(name = "CARDPAYMENTSUCCESSFUL", nullable = true)
	private Boolean cardPaymentSuccessful;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public TypePayment getType() {
		return type;
	}
	
	public void setType(TypePayment type) {
		this.type = type;
	}
	
	public Card getCard() {
		return card;
	}
	
	public void setCard(Card card) {
		this.card = card;
	}	
	
	public String getBoletoNumber() {
		return boletoNumber;
	}
	
	public void setBoletoNumber(String boletoNumber) {
		this.boletoNumber = boletoNumber;
	}
	
	public Boolean getCardPaymentSucessful() {
		return cardPaymentSuccessful;
	}
	
	public void setCardPaymentSucessful(Boolean cardPaymentSucessful) {
		this.cardPaymentSuccessful = cardPaymentSucessful;
	}
}
