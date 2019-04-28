package com.wirecard.challenge.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.wirecard.challenge.WirecardbackendchallengeApplicationTests;
import com.wirecard.challenge.model.Card;
import com.wirecard.challenge.model.CardId;
import com.wirecard.challenge.services.CardServices;

public class CardControllerTest extends WirecardbackendchallengeApplicationTests  {
	
private MockMvc mockMvc;
	
	@Autowired
	private CardController cardController;
	
	@Autowired
	private CardServices cardServices;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(cardController).build();
	}
	
	@Test
	public void testToList() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/card"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testToSave() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/card")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"cardId\" : {\"holderName\" : \"CLIENT N ONE\",	\"numberCard\" : \"5501107883854545\", \"expirationDate\" : \"2020-06-21 23:59:59\", \"cvv\" : \"981\"}}"));
	}
	
	@Test
	public void testToSearch() throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Card card = cardServices.toSave(new Card(new CardId("CLIENT THREE N ", "5225518832402530", (Date)format.parse("2021-03-27 23:59:59"), "610")));
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/api/card/holderName=" + card.getCardId().getHolderName() + "/numberCard=" + card.getCardId().getNumberCard() + "/expirationDate=" + format.format(card.getCardId().getExpirationDate()) + "/cvv=" + card.getCardId().getCvv()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	public void testToDelete() throws Exception{
		Card card = cardServices.toSave(new Card(new CardId("CLIENT N TWO", "5236655172483766", (Date)new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-03-27 23:59:59"), "430")));
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/holderName=" + card.getCardId().getHolderName() + "/numberCard=" + card.getCardId().getNumberCard() + "/expirationDate=" +  card.getCardId().getExpirationDate() + "/cvv=" + card.getCardId().getCvv()));
	}
	
}
