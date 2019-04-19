package com.wirecard.challenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Buy {
	
	@Id
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "BUYER", referencedColumnName = "CPF")
	private Buyer buyer;
	
	@OneToOne
	@JoinColumn(name = "PAYMENT", referencedColumnName = "ID")
	private Payment payment;
	
	@OneToOne
	@JoinColumn(name = "CLIENTE", referencedColumnName = "ID")
	private Client client;
	
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
