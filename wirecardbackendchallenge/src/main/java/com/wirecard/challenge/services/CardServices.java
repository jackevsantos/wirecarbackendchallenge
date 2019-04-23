package com.wirecard.challenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.wirecard.challenge.dao.CardDao;
import com.wirecard.challenge.model.Card;
import com.wirecard.challenge.model.CardId;
import com.wirecard.challenge.services.exceptions.CardNotFoundException;

@Service
public class CardServices {
	@Autowired
	CardDao cardDao;
	
	public List<Card> toList(){
		return cardDao.findAll();
	}
	
	public Card toSave(Card card) {
		return cardDao.save(card);
	}
	
	public Optional<Card> toSearch(CardId cardId){
		Optional<Card> card = cardDao.findById(cardId);
		
		if(!card.isPresent()) {
			throw new CardNotFoundException();
		}
		
		return card;
	}
	
	public void toUpdate(Card card) {
		ifExists(card);
		cardDao.save(card);
	}
	
	private void ifExists(Card card) {
		toSearch(card.getCardId());
	}
	
	public void toDelete(CardId cardId) {
		try {
			cardDao.deleteById(cardId);
		}catch (EmptyResultDataAccessException e) {
			throw new CardNotFoundException();
		}
	}
}
