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

import com.wirecard.challenge.model.Buy;
import com.wirecard.challenge.services.BuyServices;

@RestController
@RequestMapping("api/buy")
public class BuyController {
	
	@Autowired
	private BuyServices buyServices; 
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Buy>> toList() {
		return ResponseEntity.status(HttpStatus.OK).body(buyServices.toList());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> toSave(@RequestBody Buy buy) { 
		buy = buyServices.toSave(buy);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id={id}").buildAndExpand(buy.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.GET)
	public ResponseEntity<?> toSearch(@PathVariable("id") Long id) {
		Optional<Buy> buy = buyServices.toSearch(id);
		return ResponseEntity.status(HttpStatus.OK).body(buy);	
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> toUpdate(@RequestBody Buy buy, @PathVariable("id") Long id) {
		buy.setId(id);
		buyServices.toUpdate(buy);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> toDelete(@PathVariable("id") Long id) {
		buyServices.toDelete(id);
		return ResponseEntity.noContent().build();
	}
	
}
