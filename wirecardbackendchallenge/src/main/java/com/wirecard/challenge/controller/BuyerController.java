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

import com.wirecard.challenge.dao.BuyerDao;
import com.wirecard.challenge.model.Buyer;

@RestController
@RequestMapping("api/buyer")
public class BuyerController {

	@Autowired
	private BuyerDao buyerDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Buyer>> toList() {
		return ResponseEntity.status(HttpStatus.OK).body(buyerDao.findAll());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> toSave(@RequestBody Buyer buyer) { 
		buyer = buyerDao.save(buyer);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/cpf={cpf}").buildAndExpand(buyer.getCpf()).toUri();
			
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/cpf={cpf}", method = RequestMethod.GET)
	public ResponseEntity<?> toSearch(@PathVariable("cpf") String cpf) {
		Optional<Buyer> buyer = buyerDao.findById(cpf);
		
		if(!buyer.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(buyer);	
	}
	
	@RequestMapping(value = "/cpf={cpf}", method = RequestMethod.PUT)
	public ResponseEntity<Void> toUpdate(@RequestBody Buyer buyer, @PathVariable("cpf") String cpf) {
		if(buyerDao.findById(cpf).isPresent()) {
			buyer.setCpf(cpf);
			buyerDao.save(buyer);	
			
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(value = "/cpf={cpf}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> toDelete(@PathVariable("cpf") String cpf) {
		try {
			buyerDao.deleteById(cpf);
		}catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build(); 
		}		
		
		return ResponseEntity.noContent().build();
	}
}
