package com.wirecard.challenge.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wirecard.challenge.model.Client;

@Repository
public interface ClientDao extends JpaRepository<Client, Long>{

}
