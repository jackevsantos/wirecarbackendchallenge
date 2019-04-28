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
import com.wirecard.challenge.util.TypePayment;

public class PaymentControllerTest extends WirecardbackendchallengeApplicationTests {
	
private MockMvc mockMvc;
	
	@Autowired
	private PaymentController paymentController;
	
	@Autowired
	private PaymentServices paymentServices;
	
	@Autowired
	private CardServices cardServices;
	
	@Autowired
	private ClientServices clientServices;
	
	@Autowired
	private BuyerServices buyerServices;
	
	@Autowired
	private BuyServices buyServices;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
	}
	
	@Test
	public void testToList() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/payment"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testToSavePaymentCreditCard() throws Exception{
		Card card = cardServices.toSave(new Card(new CardId("CLIENT N", "5421771120099086", (Date)new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-09-27 23:59:59"), "646")));
		Client client = clientServices.toSave(new Client("Client One Test Buy"));
		Buyer buyer= buyerServices.toSave(new Buyer("62789026050", "Buyer One Test Buy", "buyer1testbuy@email.com"));
		Buy buy = buyServices.toSave(new Buy(buyer, client));
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/payment")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"amount\" : \"25.00\", "
						+ "\"typePayment\" : \"CREDIT_CARD\", "
						+ "\"card\" : {\"cardId\": {\"holderName\":" + card.getCardId().getHolderName() + ", \"numberCard\": " + card.getCardId().getNumberCard() + ", \"expirationDate\": " +  card.getCardId().getExpirationDate() + ", \"cvv\": " + card.getCardId().getCvv() + " }},	"
						+ "\"cardPaymentSuccessful\" : \"true\", "
						+ "\"buy\" :" + buy.getId() + " }"));
	}
	
	@Test
	public void testToSaveBoleto() throws Exception{
		Client client = clientServices.toSave(new Client("Client One Test Buy"));
		Buyer buyer= buyerServices.toSave(new Buyer("62789026050", "Buyer One Test Buy", "buyer1testbuy@email.com"));
		Buy buy = buyServices.toSave(new Buy(buyer, client));
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/payment")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"amount\" : \"350.00\", "
						+ "\"typePayment\" : \"BOLETO\", "
						+ "\"boletoNumber\" : \"23790504004199042023806008109206578680000035000\","
						+ "\"buy\" : " + buy.getId() + " }"));
	}
	
	@Test
	public void testToSearch() throws Exception{
		Client client = clientServices.toSave(new Client("Client One Test Buy"));
		Buyer buyer= buyerServices.toSave(new Buyer("62789026050", "Buyer One Test Buy", "buyer1testbuy@email.com"));
		Buy buy = buyServices.toSave(new Buy(buyer, client));
		Payment payment = paymentServices.toSave(new Payment(new BigDecimal(15.00), TypePayment.BOLETO, null, "23790504004199042816526008109204178760000001500", null, buy));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/payment/id=" + payment.getId()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testToUpdate() throws Exception{
		Card card = cardServices.toSave(new Card(new CardId("CLIENT N", "5421771120099086", (Date)new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-09-27 23:59:59"), "646")));
		Client client = clientServices.toSave(new Client("Client One Test Buy"));
		Buyer buyer= buyerServices.toSave(new Buyer("62789026050", "Buyer One Test Buy", "buyer1testbuy@email.com"));
		Buy buy = buyServices.toSave(new Buy(buyer, client));
		Payment payment = paymentServices.toSave(new Payment(new BigDecimal(15.00), TypePayment.CREDIT_CARD, card, null, false, buy));
		
		this.mockMvc.perform(MockMvcRequestBuilders.put("/api/payment/id=" + payment.getId()));	
	}
	
	@Test
	public void testToDelete() throws Exception{
		Client client = clientServices.toSave(new Client("Client One Test Buy"));
		Buyer buyer= buyerServices.toSave(new Buyer("62789026050", "Buyer One Test Buy", "buyer1testbuy@email.com"));
		Buy buy = buyServices.toSave(new Buy(buyer, client));
		Payment payment = paymentServices.toSave(new Payment(new BigDecimal(15.00), TypePayment.BOLETO, null, "23790504004199042816526008109204178760000001500", null, buy));
		
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/payment/id=" + payment.getId()));
	}
}
