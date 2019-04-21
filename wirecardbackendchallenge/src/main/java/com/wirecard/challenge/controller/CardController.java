package com.wirecard.challenge.controller;

import java.util.Date;
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

import com.wirecard.challenge.dao.CardDao;
import com.wirecard.challenge.model.Card;
import com.wirecard.challenge.model.CardId;

@RestController
@RequestMapping("api/card")
public class CardController {
	
	@Autowired
	private CardDao cardDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Card> toList() {
		return cardDao.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void toSave(@RequestBody Card card) { 
		cardDao.save(card);
	}
	
	@RequestMapping(value = "holderName={holderName}/numberCard={numberCard}/expirationDate={expirationDate}/cvv={cvv}", method = RequestMethod.GET)
	public ResponseEntity<?> toSearch(@PathVariable("holderName") String holderName,
									  @PathVariable("numberCard") String numberCard,
									  @PathVariable("expirationDate") Date expirationDate,
									  @PathVariable("cvv") String cvv) {
		
		Optional<Card> card = cardDao.findById(new CardId(holderName, numberCard, expirationDate, cvv));
		
		if(!card.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(card);	
	}
	
}
