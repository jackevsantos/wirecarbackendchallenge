package com.wirecard.challenge.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wirecard.challenge.dao.CardDao;
import com.wirecard.challenge.model.Card;
import com.wirecard.challenge.model.CardId;

@RestController
@RequestMapping("api/card")
public class CardController {
	
	@Autowired
	private CardDao cardDao;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Card>> toList() {
		return ResponseEntity.status(HttpStatus.OK).body(cardDao.findAll());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> toSave(@RequestBody Card card) { 
		card = cardDao.save(card);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/holderName={holderName}/numberCard={numberCard}/expirationDate={expirationDate}/cvv={cvv}")
					.buildAndExpand(card.getCardId().getHolderName(), card.getCardId().getNumberCard(), card.getCardId().getExpirationDate(), card.getCardId().getClass()).toUri();
			
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/holderName={holderName}/numberCard={numberCard}/expirationDate={expirationDate}/cvv={cvv}", method = RequestMethod.GET)
	public ResponseEntity<?> toSearch(@PathVariable("holderName") String holderName,
									  @PathVariable("numberCard") String numberCard,
									  @PathVariable("expirationDate") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date expirationDate,
									  @PathVariable("cvv") String cvv) {
		
		Optional<Card> card = cardDao.findById(new CardId(holderName, numberCard, expirationDate, cvv));
		
		if(!card.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(card);	
	}
	
	@RequestMapping(value = "/holderName={holderName}/numberCard={numberCard}/expirationDate={expirationDate}/cvv={cvv}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> toDelete(@PathVariable("holderName") String holderName,
									  @PathVariable("numberCard") String numberCard,
									  @PathVariable("expirationDate") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date expirationDate,
									  @PathVariable("cvv") String cvv) {
		
		try {
			cardDao.deleteById(new CardId(holderName, numberCard, expirationDate, cvv));
		}catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build(); 
		}		
		
		return ResponseEntity.noContent().build();
	}
	
	
}
