package com.wirecard.challenge.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wirecard.challenge.model.Card;
import com.wirecard.challenge.model.CardId;

@Repository
public interface CardDao extends JpaRepository<Card, CardId>{
	public Optional<Card> findByCardId(CardId cardId);
}
