package com.wirecard.challenge.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.wirecard.challenge.WirecardbackendchallengeApplicationTests;
import com.wirecard.challenge.model.Client;
import com.wirecard.challenge.services.ClientServices;

public class ClientControllerTest extends WirecardbackendchallengeApplicationTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private ClientController clientController;
	
	@Autowired
	private ClientServices clientServices;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
	}
	
	@Test
	public void testToList() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/client"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testToSave() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/client")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\" : \"Client Number One Test\"}"));
	}
	
	@Test
	public void testToSearch() throws Exception{
		Client client = clientServices.toSave(new Client("Client To Search"));
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/client/id=" + client.getId()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testToUpdate() throws Exception{
		Client client = clientServices.toSave(new Client("Client To Update"));
		this.mockMvc.perform(MockMvcRequestBuilders.put("/api/client/id=" + client.getId()));
	}
	
	@Test
	public void testToDelete() throws Exception{
		Client client = clientServices.toSave(new Client("Client To Delete"));
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/client/id=" + client.getId()));
	}
	
}
