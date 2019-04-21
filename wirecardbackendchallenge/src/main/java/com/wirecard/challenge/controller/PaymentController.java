package com.wirecard.challenge.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wirecard.challenge.dao.PaymentDao;
import com.wirecard.challenge.model.Payment;

@RestController
@RequestMapping("api/payment")
public class PaymentController {
	
	@Autowired
	private PaymentDao paymentDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Payment>> toList() {
		return ResponseEntity.status(HttpStatus.OK).body(paymentDao.findAll());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> toSave(@RequestBody Payment payment) { 
		payment = paymentDao.save(payment);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id={id}").buildAndExpand(payment.getId()).toUri();
			
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.GET)
	public ResponseEntity<?> toSearch(@PathVariable("id") Long id) {
		Optional<Payment> payment = paymentDao.findById(id);
		
		if(!payment.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(payment);	
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> toUpdate(@RequestBody Payment payment, @PathVariable("id") Long id) {
		if(paymentDao.findById(id).isPresent()) {
			payment.setId(id);
			paymentDao.save(payment);	
			
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> toDelete(@PathVariable("id") Long id) {
		try {
			paymentDao.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build(); 
		}		
		
		return ResponseEntity.noContent().build();
	}
}
