package com.wirecard.challenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.wirecard.challenge.dao.ClientDao;
import com.wirecard.challenge.model.Client;
import com.wirecard.challenge.services.exceptions.ClientNotFoundException;

@Service
public class ClientServices {
	@Autowired
	ClientDao clientDao;
	
	public List<Client> toList(){
		return clientDao.findAll();
	}
	
	public Client toSave(Client client) {
		client.setId(null);
		return clientDao.save(client);
	}
	
	public Optional<Client> toSearch(Long id){
		Optional<Client> client = clientDao.findById(id);
		
		if(!client.isPresent()) {
			throw new ClientNotFoundException();
		}
		
		return client;
	}
	
	public void toUpdate(Client client) {
		ifExists(client);
		clientDao.save(client);
	}
	
	private void ifExists(Client client) {
		toSearch(client.getId());
	}
	
	public void toDelete(Long id) {
		try {
			clientDao.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ClientNotFoundException();
		}
	}
}
