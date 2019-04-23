package com.wirecard.challenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.wirecard.challenge.dao.BuyDao;
import com.wirecard.challenge.model.Buy;
import com.wirecard.challenge.services.exceptions.BuyNotFoundException;

@Service
public class BuyServices {
	@Autowired
	BuyDao buyDao;

	public List<Buy> toList(){
		return buyDao.findAll();
	}
	
	public Buy toSave(Buy buy) {
		buy.setId(null);
		return buyDao.save(buy);
	}
	
	public Optional<Buy> toSearch(Long id){
		Optional<Buy> buy = buyDao.findById(id);
		
		if(!buy.isPresent()) {
			throw new BuyNotFoundException();
		}
		
		return buy;
	}
	
	public void toUpdate(Buy buy) {
		ifExists(buy);
		buyDao.save(buy);
	}
	
	private void ifExists(Buy buy) {
		toSearch(buy.getId());
	}
	
	public void toDelete(Long id) {
		try {
			buyDao.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new BuyNotFoundException();
		}
	}
}
