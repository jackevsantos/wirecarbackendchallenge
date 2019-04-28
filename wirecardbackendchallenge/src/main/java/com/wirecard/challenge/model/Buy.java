package com.wirecard.challenge.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Buy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "BUYER", nullable = false)
	private Buyer buyer;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="buy")
	@JsonIgnore
	private List<Payment> payments;
	
	@OneToOne
	@JoinColumn(name = "CLIENT", nullable = false)
	private Client client;
	
	public Buy() {}
	
	public Buy(Buyer buyer, Client client) {
		this.buyer = buyer;
		this.client = client;
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
	public List<Payment> getPayments() {
		return payments;
	}
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
}
