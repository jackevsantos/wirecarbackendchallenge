package com.wirecard.challenge.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wirecard.challenge.model.ErrorDetails;
import com.wirecard.challenge.services.exceptions.BuyNotFoundException;
import com.wirecard.challenge.services.exceptions.BuyerNotFoundException;
import com.wirecard.challenge.services.exceptions.CardNotFoundException;
import com.wirecard.challenge.services.exceptions.ClientNotFoundException;
import com.wirecard.challenge.services.exceptions.ExistingBuyerException;
import com.wirecard.challenge.services.exceptions.ExistingCardException;
import com.wirecard.challenge.services.exceptions.NumberCardInvalidException;
import com.wirecard.challenge.services.exceptions.PaymentNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(BuyNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleBuyNotFoundException(BuyNotFoundException buyNotFoundException, HttpServletRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(404l);
		errorDetails.setTitle("The buy was not found!");
		errorDetails.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
	}
	
	@ExceptionHandler(BuyerNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleBuyerNotFoundException(BuyerNotFoundException buyerNotFoundException, HttpServletRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(404l);
		errorDetails.setTitle("The buyer was not found!");
		errorDetails.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
	}
	
	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleClientNotFoundException(ClientNotFoundException clientNotFoundException, HttpServletRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(404l);
		errorDetails.setTitle("The client was not found!");
		errorDetails.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
	}
	
	@ExceptionHandler(CardNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleCardNotFoundException(CardNotFoundException cardNotFoundException, HttpServletRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(404l);
		errorDetails.setTitle("The card was not found!");
		errorDetails.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
	}
	
	@ExceptionHandler(PaymentNotFoundException.class)
	public ResponseEntity<ErrorDetails> handlePaymentNotFoundException(PaymentNotFoundException paymentNotFoundException, HttpServletRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(404l);
		errorDetails.setTitle("The payment was not found!");
		errorDetails.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorDetails> handleDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException, HttpServletRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(400l);
		errorDetails.setTitle("Invalid Request! You must modify the request to try again.");
		errorDetails.setTimestamp(System.currentTimeMillis());
		errorDetails.setCause(dataIntegrityViolationException.getCause().getCause().toString());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
	}
	
	@ExceptionHandler(JpaObjectRetrievalFailureException.class)
	public ResponseEntity<ErrorDetails> handleJpaObjectRetrievalFailureException(JpaObjectRetrievalFailureException jpaObjectRetrievalFailureException, HttpServletRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(400l);
		errorDetails.setTitle("Invalid Request! You must modify the request to try again." + jpaObjectRetrievalFailureException.getCause().getCause());
		errorDetails.setTimestamp(System.currentTimeMillis());
		errorDetails.setCause(jpaObjectRetrievalFailureException.getCause().getCause().toString());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
	}
	
	@ExceptionHandler(ExistingCardException.class)
	public ResponseEntity<ErrorDetails> handleExistingCardException(ExistingCardException existingCardException, HttpServletRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(400l);
		errorDetails.setTitle("This card has already been registered!");
		errorDetails.setTimestamp(System.currentTimeMillis());
		errorDetails.setCause("A card with these identifiers already exists.");
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
	}
	
	@ExceptionHandler(ExistingBuyerException.class)
	public ResponseEntity<ErrorDetails> handleExistingBuyerException(ExistingBuyerException existingBuyerException, HttpServletRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(400l);
		errorDetails.setTitle("This buyer has already been registered!");
		errorDetails.setTimestamp(System.currentTimeMillis());
		errorDetails.setCause("There is already a registered buyer with this CPF.");
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
	}
	
	@ExceptionHandler(NumberCardInvalidException.class)
	public ResponseEntity<ErrorDetails> numberCardInvalidException(NumberCardInvalidException numberCardInvalidException, HttpServletRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(400l);
		errorDetails.setTitle("Card Invalid!");
		errorDetails.setTimestamp(System.currentTimeMillis());
		errorDetails.setCause("Invalid card number, please check and try again.");
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
	}
}
