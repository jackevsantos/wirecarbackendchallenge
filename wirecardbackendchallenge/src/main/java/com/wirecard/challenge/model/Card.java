package com.wirecard.challenge.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Card {
	
	@EmbeddedId
	private CardId cardId;
	
}
