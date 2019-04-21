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

import com.wirecard.challenge.dao.BuyDao;
import com.wirecard.challenge.model.Buy;

@RestController
@RequestMapping("api/buy")
public class BuyController {
	
	@Autowired
	private BuyDao buyDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Buy> toList() {
		return buyDao.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void toSave(@RequestBody Buy buy) { 
		buyDao.save(buy);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> toSearch(@PathVariable("id") Long id) {
		Optional<Buy> buy = buyDao.findById(id);
		
		if(!buy.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(buy);	
	}
	
}
