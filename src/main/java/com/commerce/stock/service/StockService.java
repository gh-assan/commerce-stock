package com.commerce.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commerce.stock.entity.Stock;
import com.commerce.stock.exception.OutdatedStockException;
import com.commerce.stock.repository.StockRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StockService {
	
	@Autowired
	private StockRepository repo;
	
	@Autowired
	ObjectMapper objectMapper;
	

	public StockService() {
		super();
	}
	
	
	public Stock updateStock(Stock stock) throws OutdatedStockException {
		
		Stock previous  = repo.findByProductId(stock.getProductId());
		
		if (previous!= null ){
			
			try {
				String oldTimeStamp = objectMapper.writeValueAsString(previous.getTimestamp());
				String newtimestamp = objectMapper.writeValueAsString(stock.getTimestamp());
				System.out.println(oldTimeStamp + " ==  " +newtimestamp);
				if (!oldTimeStamp.equals(newtimestamp)) {					
					throw new OutdatedStockException();
				}
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}
		
		stock.setTimestamp();
		
		stock = repo.save(stock);
		
		return stock;
	}
}
