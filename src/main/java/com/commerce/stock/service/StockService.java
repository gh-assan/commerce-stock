package com.commerce.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.commerce.stock.entity.SoldItem;
import com.commerce.stock.entity.Stock;
import com.commerce.stock.exception.OutdatedStockException;
import com.commerce.stock.exception.ProductNotFoundException;
import com.commerce.stock.repository.SoldItemRepository;
import com.commerce.stock.repository.StockRepository;
import com.commerce.stock.util.date.TimeSpanResolver;
import com.commerce.stock.valueObject.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StockService {
	
	@Autowired
	private StockRepository repo;
	
	@Autowired
	private SoldItemRepository itemRepo;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	TimeSpanResolver timeSpanResolver;
	

	public StockService() {
		super();
	}
	
	
	@Transactional
	public Stock updateStock(Stock stock) throws OutdatedStockException {
		
		Stock previous  = repo.findByProductId(stock.getProductId());
		
		if (previous!= null ){
			
			try {
				String oldTimeStamp = objectMapper.writeValueAsString(previous.getTimestamp());
				String newtimestamp = objectMapper.writeValueAsString(stock.getTimestamp());
				if (!oldTimeStamp.equals(newtimestamp)) {					
					throw new OutdatedStockException();
				}
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}
		
		if (previous!= null)
			previous = new Stock (previous);
		
		stock.setTimestamp();		
		stock = repo.save(stock);
		
		if (previous!= null)
			System.out.println(previous + " " + stock +  " " + (previous.getQuantity() > stock.getQuantity() ) );
		
		if (previous!= null && previous.getQuantity() > stock.getQuantity() ){
			
			SoldItem item = new SoldItem();
			item.setProductId(stock.getProductId());
			item.setTimestamp();
			item.setQuantity(previous.getQuantity() - stock.getQuantity());
			
			itemRepo.save(item);
		}
		
		
		return stock;
	}
	
	public Product getProduct(String productId) throws ProductNotFoundException{
		
		Stock stock = repo.findByProductId(productId);
		
		if (stock == null)
			throw new ProductNotFoundException();
		
		return new Product (stock);		
	}
	
	
	public void deleteAll(){
		
		repo.deleteAll();
		itemRepo.deleteAll();
		
		
	}
	
}
