package com.wirecard.challenge.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wirecard.challenge.dao.ClientDao;
import com.wirecard.challenge.model.Client;

@RestController
@RequestMapping("api/client")
public class ClientController {
	
	@Autowired
	private ClientDao clientDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Client> toList() {
		return clientDao.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void toSave(@RequestBody Client client) { 
		clientDao.save(client);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> toSearch(@PathVariable("id") Long id) {
		Optional<Client> client = clientDao.findById(id);
		
		if(!client.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(client);	
	}
}
