package com.wirecard.challenge.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wirecard.challenge.model.Buyer;
import com.wirecard.challenge.services.BuyerServices;

@RestController
@RequestMapping("api/buyer")
public class BuyerController {

	@Autowired
	private BuyerServices buyerservices;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Buyer>> toList() {
		return ResponseEntity.status(HttpStatus.OK).body(buyerservices.toList());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> toSave(@RequestBody Buyer buyer) { 
		buyer = buyerservices.toSave(buyer);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/cpf={cpf}").buildAndExpand(buyer.getCpf()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/cpf={cpf}", method = RequestMethod.GET)
	public ResponseEntity<?> toSearch(@PathVariable("cpf") String cpf) {
		Optional<Buyer> buyer = buyerservices.toSearch(cpf);
		return ResponseEntity.status(HttpStatus.OK).body(buyer);	
	}
	
	@RequestMapping(value = "/cpf={cpf}", method = RequestMethod.PUT)
	public ResponseEntity<Void> toUpdate(@RequestBody Buyer buyer, @PathVariable("cpf") String cpf) {
		buyer.setCpf(cpf);
		buyerservices.toUpdate(buyer);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/cpf={cpf}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> toDelete(@PathVariable("cpf") String cpf) {
		buyerservices.toDelete(cpf);
		return ResponseEntity.noContent().build();
	}
}
