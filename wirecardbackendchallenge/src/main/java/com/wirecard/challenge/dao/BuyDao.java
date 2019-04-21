package com.wirecard.challenge.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wirecard.challenge.model.Buy;

@Repository
public interface BuyDao extends JpaRepository<Buy, Long>{

}
