package com.wirecard.challenge.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wirecard.challenge.model.Payment;
import com.wirecard.challenge.services.PaymentServices;

@RestController
@RequestMapping("api/payment")
public class PaymentController {
	
	@Autowired
	private PaymentServices paymentServices;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Payment>> toList() {
		return ResponseEntity.status(HttpStatus.OK).body(paymentServices.toList());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> toSave(@Valid @RequestBody Payment payment) { 
		payment = paymentServices.toSave(payment);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id={id}").buildAndExpand(payment.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.GET)
	public ResponseEntity<?> toSearch(@PathVariable("id") Long id) {
		Optional<Payment> payment = paymentServices.toSearch(id);
		return ResponseEntity.status(HttpStatus.OK).body(payment);	
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> toUpdate(@Valid @RequestBody Payment payment, @PathVariable("id") Long id) {
		payment.setId(id);
		paymentServices.toUpdate(payment);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> toDelete(@PathVariable("id") Long id) {
		paymentServices.toDelete(id);
		return ResponseEntity.noContent().build();
	}
	
}
