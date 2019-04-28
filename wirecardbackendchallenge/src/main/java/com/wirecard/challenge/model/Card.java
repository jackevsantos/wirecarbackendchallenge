package com.wirecard.challenge.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Card {
	
	@EmbeddedId
	private CardId cardId;
	
	public Card() {	}
	
	public Card(CardId cardId) {
		this.cardId = cardId;
	}
	
	public CardId getCardId() {
		return cardId;
	}
	
	public void setCardId(CardId cardId) {
		this.cardId = cardId;
	}
	
}
