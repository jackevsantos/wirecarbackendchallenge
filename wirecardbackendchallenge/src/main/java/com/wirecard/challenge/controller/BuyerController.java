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

import com.wirecard.challenge.dao.BuyerDao;
import com.wirecard.challenge.model.Buyer;

@RestController
@RequestMapping("api/buyer")
public class BuyerController {

	@Autowired
	private BuyerDao buyerDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Buyer> toList() {
		return buyerDao.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void toSave(@RequestBody Buyer buyer) { 
		buyerDao.save(buyer);
	}
	
	@RequestMapping(value = "/{cpf}", method = RequestMethod.GET)
	public ResponseEntity<?> toSearch(@PathVariable("cpf") String cpf) {
		Optional<Buyer> buyer = buyerDao.findById(cpf);
		
		if(!buyer.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(buyer);	
	}
}
