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
import com.wirecard.challenge.model.Buyer;
import com.wirecard.challenge.services.BuyerServices;

public class BuyerControllerTest extends WirecardbackendchallengeApplicationTests {

	private MockMvc mockMvc;
	
	@Autowired
	private BuyerController buyerController;
	
	@Autowired
	private BuyerServices buyerServices;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(buyerController).build();
	}
	
	@Test
	public void testToList() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/buyer"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testToSave() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.post("api/buyer")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"cpf\" : \"50780393090\", \"name\" : \"Buyer Number One Test\", \"email\" : \"buyernumberonetest@email.com\"}"));
	}
	
	@Test
	public void testToSearch() throws Exception{
		Buyer buyer= buyerServices.toSave(new Buyer("86414142077", "Buyer to Search", "buyertosearch@email.com"));
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/buyer/cpf=" + buyer.getCpf()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testToUpdate() throws Exception{
		Buyer buyer= buyerServices.toSave(new Buyer("80014253046", "Buyer to Update", "buyertoupdate@email.com"));
		this.mockMvc.perform(MockMvcRequestBuilders.put("/api/buyer/cpf=" + buyer.getCpf()));
	}
	
	@Test
	public void testToDelete() throws Exception{
		Buyer buyer= buyerServices.toSave(new Buyer("12093860031", "Buyer to Delete", "buyertodelete@email.com"));
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/buyer/cpf=" + buyer.getCpf()));
	}
	
}
