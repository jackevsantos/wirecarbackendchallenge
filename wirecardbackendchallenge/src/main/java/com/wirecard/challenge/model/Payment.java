package com.wirecard.challenge.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wirecard.challenge.util.TypePayment;

@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "AMOUNT", nullable = false)
	private BigDecimal amount;
	
	@Column(name = "TYPE", nullable = false)
	private String type;
	
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumns({
	  @JoinColumn(name = "HOLDERNAME"),
	  @JoinColumn(name = "NUMBERCARD"),
	  @JoinColumn(name = "EXPIRATIONDATE"),
	  @JoinColumn(name = "CVV"),
	})
	@JsonInclude(Include.NON_NULL)
	private Card card;
	
	@Column(name = "BOLETONUMBER")
	@JsonInclude(Include.NON_NULL)
	private String boletoNumber;
	
	@Column(name = "CARDPAYMENTSUCCESSFUL", columnDefinition = "BOOLEAN")
	@JsonInclude(Include.NON_NULL)
	private Boolean cardPaymentSuccessful;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUYID")
	@JsonInclude(Include.NON_NULL)
	private Buy buy;
	
	public Payment() { }
	
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
	
	public String getType() {
		return type;
	}
	
	public void setType(TypePayment typePayment) {
		this.type = typePayment.getDescription();
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
	
	public Buy getBuy() {
		return buy;
	}
	
	public void setBuy(Buy buy) {
		this.buy = buy;
	}
}
