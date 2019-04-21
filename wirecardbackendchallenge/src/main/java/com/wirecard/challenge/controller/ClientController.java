package com.wirecard.challenge.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wirecard.challenge.dao.ClientDao;
import com.wirecard.challenge.model.Client;

@RestController
@RequestMapping("api/client")
public class ClientController {
	
	@Autowired
	private ClientDao clientDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Client>> toList() {
		return ResponseEntity.status(HttpStatus.OK).body(clientDao.findAll());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> toSave(@RequestBody Client client) { 
		client = clientDao.save(client);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id={id}").buildAndExpand(client.getId()).toUri();
			
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.GET)
	public ResponseEntity<?> toSearch(@PathVariable("id") Long id) {
		Optional<Client> client = clientDao.findById(id);
		
		if(!client.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(client);	
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> toUpdate(@RequestBody Client client, @PathVariable("id") Long id) {
		if(clientDao.findById(id).isPresent()) {
			client.setId(id);
			clientDao.save(client);	
			
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> toDelete(@PathVariable("id") Long id) {
		try {
			clientDao.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build(); 
		}		
		
		return ResponseEntity.noContent().build();
	}
	
}
