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
import com.wirecard.challenge.services.exceptions.NumberCardInvalidException;

@Service
public class CardServices {
	@Autowired
	CardDao cardDao;
	
	public List<Card> toList(){
		return cardDao.findAll();
	}
	
	public Card toSave(Card card) {
		validateCreditCardNumber(card.getCardId().getNumberCard());
		ifExists(card);
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
		validateCreditCardNumber(card.getCardId().getNumberCard());
		ifExists(card);
		cardDao.save(card);
	}
	
	private void ifExists(Card card) {
		cardDao.findById(card.getCardId()).isPresent();
	}
	
	public void toDelete(CardId cardId) {
		try {
			cardDao.deleteById(cardId);
		}catch (EmptyResultDataAccessException e) {
			throw new CardNotFoundException();
		}
	}
	
	public void validateCreditCardNumber(String numberCreditCard) {
		if(numberCreditCard.length()==16){
			
			int[] ints = new int[numberCreditCard.length()];
			
			for (int i = 0; i < numberCreditCard.length(); i++) {
				ints[i] = Integer.parseInt(numberCreditCard.substring(i, i + 1));
			}
			
			for (int i = ints.length - 2; i >= 0; i = i - 2) {
				int j = ints[i];
				j = j * 2;
				if (j > 9) {
					j = j % 10 + 1;
				}
				ints[i] = j;
			}
			
			int sum = 0;
			for (int i = 0; i < ints.length; i++) {
				sum += ints[i];
			}
		    
		    if(sum%10 != 0) {		       
		    	throw new NumberCardInvalidException();
		    }
		} else {
			throw new NumberCardInvalidException();
		}
	}
}
