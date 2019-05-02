package com.wirecard.challenge.controller;

import java.math.BigDecimal;
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
import com.wirecard.challenge.model.Buy;
import com.wirecard.challenge.model.Buyer;
import com.wirecard.challenge.model.Card;
import com.wirecard.challenge.model.CardId;
import com.wirecard.challenge.model.Client;
import com.wirecard.challenge.model.Payment;
import com.wirecard.challenge.services.BuyServices;
import com.wirecard.challenge.services.BuyerServices;
import com.wirecard.challenge.services.CardServices;
import com.wirecard.challenge.services.ClientServices;
import com.wirecard.challenge.services.PaymentServices;
import com.wirecard.challenge.util.StatusPaymentBoleto;
import com.wirecard.challenge.util.StatusPaymentCard;
import com.wirecard.challenge.util.TypePayment;

public class BuyControllerTest extends WirecardbackendchallengeApplicationTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private BuyController buyController;
	
	@Autowired
	private BuyServices buyServices;
	
	@Autowired
	private ClientServices clientServices;
	
	@Autowired
	private BuyerServices buyerServices;
	
	@Autowired
	private PaymentServices paymentServices;
	
	@Autowired
	private CardServices cardServices;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(buyController).build();
	}
	
	@Test
	public void testToList() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/buy"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testToSave() throws Exception{
		Client client = clientServices.toSave(new Client("Client One Test Buy"));
		Buyer buyer= buyerServices.toSave(new Buyer("62789026050", "Buyer One Test Buy", "buyer1testbuy@email.com"));
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/buy")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"buyer\" : {\"cpf\" : " + buyer.getCpf() + " },  \"client\" : {\"id\" :" + client.getId() + "}}"));
	}
	
	@Test
	public void testToSearch() throws Exception{
		Client client = clientServices.toSave(new Client("Client Two Test Buy"));
		Buyer buyer= buyerServices.toSave(new Buyer("87262593030", "Buyer Two Test Buy", "buyer2testbuy@email.com"));
		Payment payment = paymentServices.toSave(new Payment(new BigDecimal(15.00), TypePayment.BOLETO, null, "23790504004199042816526008109204178760000001500", null, StatusPaymentBoleto.AWAITING_PAYMENT, null));
		Buy buy = buyServices.toSave(new Buy(buyer, client, payment));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/buy/id=" + buy.getId()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testToUpdate() throws Exception{
		Client client = clientServices.toSave(new Client("Client Three Test Buy"));
		Buyer buyer= buyerServices.toSave(new Buyer("54305172089", "Buyer Three Test Buy", "buyer3testbuy@email.com"));
		Card card = cardServices.toSave(new Card(new CardId("CLIENT N", "5241583723359454", (Date)new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-09-27 23:59:59"), "646")));
		Payment payment = paymentServices.toSave(new Payment(new BigDecimal(15.00), TypePayment.CREDIT_CARD, card, null, false, null, StatusPaymentCard.REFUSED));
		Buy buy = buyServices.toSave(new Buy(buyer, client, payment));
		this.mockMvc.perform(MockMvcRequestBuilders.put("/api/buy/id=" + buy.getId()));
	}
	
	@Test
	public void testToDelete() throws Exception{
		Client client = clientServices.toSave(new Client("Client Four Test Buy"));
		Buyer buyer= buyerServices.toSave(new Buyer("23784123040", "Buyer Three Four Buy", "buyer4testbuy@email.com"));
		Payment payment = paymentServices.toSave(new Payment(new BigDecimal(15.00), TypePayment.BOLETO, null, "23790504004199050122703008109203178790000360000", null, StatusPaymentBoleto.AWAITING_PAYMENT, null));
		Buy buy = buyServices.toSave(new Buy(buyer, client, payment));
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/buy/id=" + buy.getId()));
	}
	
	
	 

}
