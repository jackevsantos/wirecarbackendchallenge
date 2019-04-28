package com.wirecard.challenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.wirecard.challenge.dao.PaymentDao;
import com.wirecard.challenge.model.Payment;
import com.wirecard.challenge.services.exceptions.PaymentNotFoundException;

@Service
public class PaymentServices {
	@Autowired
	PaymentDao paymentDao;
	
	public List<Payment> toList(){
		return paymentDao.findAll();
	}
	
	public Payment toSave(Payment payment) {
		payment.setId(null);
		return paymentDao.save(payment);
	}
	
	public Optional<Payment> toSearch(Long id){
		Optional<Payment> payment = paymentDao.findById(id);
		
		if(!payment.isPresent()) {
			throw new PaymentNotFoundException();
		}
		
		return payment;
	}
	
	public void toUpdate(Payment payment) {
		ifExists(payment);
		paymentDao.save(payment);
	}
	
	private void ifExists(Payment payment) {
		toSearch(payment.getId());
	}
	
	public void toDelete(Long id) {
		try {
			paymentDao.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new PaymentNotFoundException();
		}
	}
	
}
