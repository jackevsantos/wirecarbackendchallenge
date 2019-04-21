package com.wirecard.challenge.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wirecard.challenge.model.Buyer;

@Repository
public interface BuyerDao extends JpaRepository<Buyer, String>{

}
