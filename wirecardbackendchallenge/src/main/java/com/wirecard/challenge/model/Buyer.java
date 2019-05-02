package com.wirecard.challenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Buyer {
	
	@Id
	@Column(name = "CPF", nullable = false)
	private String cpf;
	
	@Column(name = "NAME", nullable = false)
	@NotEmpty(message = "You need to inform a name.")
	private String name;
	
	@Column(name = "EMAIL")
	@JsonInclude(Include.NON_NULL)
	private String email;
	
	public Buyer() {}
	
	public Buyer(String cpf, String name, String email) {
		this.cpf = cpf;
		this.name = name;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
