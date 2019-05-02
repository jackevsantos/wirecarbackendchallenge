package com.wirecard.challenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Buy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "BUYER", nullable = false)
	@NotNull(message = "You need to inform a buyer.")
	private Buyer buyer;
	
	@OneToOne
	@JoinColumn(name = "PAYMENT", nullable = false)
	@NotNull(message = "You payment to inform a buyer.")
	private Payment payment;
	
	@OneToOne
	@JoinColumn(name = "CLIENT", nullable = false)
	@NotNull(message = "You need to inform a client.")
	private Client client;
	
	public Buy() {}
	
	public Buy(Buyer buyer, Client client, Payment payment) {
		this.buyer = buyer;
		this.client = client;
		this.payment = payment;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Buyer getBuyer() {
		return buyer;
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
}
