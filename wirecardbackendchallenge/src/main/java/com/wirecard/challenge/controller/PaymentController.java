package com.wirecard.challenge.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wirecard.challenge.dao.PaymentDao;
import com.wirecard.challenge.model.Payment;

@RestController
@RequestMapping("api/payment")
public class PaymentController {
	
	@Autowired
	private PaymentDao paymentDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Payment> toList() {
		return paymentDao.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void toSave(@RequestBody Payment payment) { 
		paymentDao.save(payment);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> toSearch(@PathVariable("id") Long id) {
		Optional<Payment> payment = paymentDao.findById(id);
		
		if(!payment.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(payment);	
	}
}
