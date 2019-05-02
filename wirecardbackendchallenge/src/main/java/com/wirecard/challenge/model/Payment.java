package com.wirecard.challenge.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wirecard.challenge.util.StatusPaymentBoleto;
import com.wirecard.challenge.util.StatusPaymentCard;
import com.wirecard.challenge.util.TypePayment;


@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "AMOUNT", nullable = false)
	@NotNull(message = "You need to inform an amount.")
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TYPEPAYMENT", nullable = false)
	@NotNull(message = "You need to inform a type of payment.")
	private TypePayment typePayment;
	
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
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUSPAYMENTBOLETO")
	@JsonInclude(Include.NON_NULL)
	private StatusPaymentBoleto statusPaymentBoleto;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUSPAYMENTCARD")
	@JsonInclude(Include.NON_NULL)
	private StatusPaymentCard statusPaymentCard;
	
	public Payment() { }
	
	public Payment(BigDecimal amount, TypePayment typePayment, Card card, String boletoNumber, Boolean cardPaymentSuccessful, 
			StatusPaymentBoleto statusPaymentBoleto, StatusPaymentCard statusPaymentCard) { 
		this.amount = amount;
		this.typePayment = typePayment;
		this.card = card;
		this.boletoNumber = boletoNumber;
		this.cardPaymentSuccessful = cardPaymentSuccessful;
		this.statusPaymentBoleto = statusPaymentBoleto;
		this.statusPaymentCard = statusPaymentCard;
	}
	
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
	
	public TypePayment getTypePayment() {
		return typePayment;
	}

	public void setTypePayment(TypePayment typePayment) {
		this.typePayment = typePayment;
	}

	public Boolean getCardPaymentSuccessful() {
		return cardPaymentSuccessful;
	}

	public void setCardPaymentSuccessful(Boolean cardPaymentSuccessful) {
		this.cardPaymentSuccessful = cardPaymentSuccessful;
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
	
	public StatusPaymentBoleto getStatusPaymentBoleto() {
		return statusPaymentBoleto;
	}

	public void setStatusPaymentBoleto(StatusPaymentBoleto statusPaymentBoleto) {
		this.statusPaymentBoleto = statusPaymentBoleto;
	}

	public StatusPaymentCard getStatusPaymentCard() {
		return statusPaymentCard;
	}

	public void setStatusPaymentCard(StatusPaymentCard statusPaymentCard) {
		this.statusPaymentCard = statusPaymentCard;
	}
	
}
