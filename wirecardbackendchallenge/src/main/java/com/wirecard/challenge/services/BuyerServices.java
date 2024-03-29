package com.wirecard.challenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.wirecard.challenge.dao.BuyerDao;
import com.wirecard.challenge.model.Buyer;
import com.wirecard.challenge.services.exceptions.BuyerNotFoundException;
import com.wirecard.challenge.services.exceptions.ExistingBuyerException;

@Service
public class BuyerServices {
	@Autowired
	BuyerDao buyerDao;
	
	public List<Buyer> toList(){
		return buyerDao.findAll();
	}
	
	public Buyer toSave(Buyer buyer) {
		if(!ifExists(buyer.getCpf())) {
			return buyerDao.save(buyer);
		}else {
			throw new ExistingBuyerException();
		}
	}
	
	public Optional<Buyer> toSearch(String cpf){
		Optional<Buyer> buyer = buyerDao.findById(cpf);
		
		if(!buyer.isPresent()) {
			throw new BuyerNotFoundException();
		}
		
		return buyer;
	}
	
	public void toUpdate(Buyer buyer) {
		if(ifExists(buyer.getCpf())) {
			buyerDao.save(buyer);
		} else {
			throw new BuyerNotFoundException();
		}
	}
	
	private Boolean ifExists(String cpf) {
		return buyerDao.findById(cpf).isPresent();
	}
	
	public void toDelete(String cpf) {
		try {
			buyerDao.deleteById(cpf);
		}catch (EmptyResultDataAccessException e) {
			throw new BuyerNotFoundException();
		}
	}
}
