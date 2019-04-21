package com.wirecard.challenge.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wirecard.challenge.model.Payment;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Long>{

}
