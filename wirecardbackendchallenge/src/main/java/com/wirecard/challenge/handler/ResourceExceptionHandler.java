package com.wirecard.challenge.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.wirecard.challenge.model.ErrorDetails;
import com.wirecard.challenge.services.exceptions.BuyNotFoundException;
import com.wirecard.challenge.services.exceptions.BuyerNotFoundException;
import com.wirecard.challenge.services.exceptions.CardNotFoundException;
import com.wirecard.challenge.services.exceptions.ClientNotFoundException;
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
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
	}
	
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorDetails> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException, HttpServletRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(400l);
		errorDetails.setTitle("Invalid Request! You must modify the request to try again.");
		errorDetails.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
	}
	
}
