package com.wirecard.challenge.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wirecard.challenge.model.Client;
import com.wirecard.challenge.services.ClientServices;

@RestController
@RequestMapping("api/client")
public class ClientController {
	
	@Autowired
	private ClientServices clientServices;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Client>> toList() {
		return ResponseEntity.status(HttpStatus.OK).body(clientServices.toList());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> toSave(@Valid @RequestBody Client client) { 
		client = clientServices.toSave(client);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id={id}").buildAndExpand(client.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.GET)
	public ResponseEntity<?> toSearch(@PathVariable("id") Long id) {
		Optional<Client> client = clientServices.toSearch(id);
		return ResponseEntity.status(HttpStatus.OK).body(client);	
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> toUpdate(@Valid @RequestBody Client client, @PathVariable("id") Long id) {
		client.setId(id);
		clientServices.toUpdate(client);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/id={id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> toDelete(@PathVariable("id") Long id) {
		clientServices.toDelete(id);
		return ResponseEntity.noContent().build();
	}
	
}
