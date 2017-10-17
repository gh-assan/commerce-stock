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
import com.commerce.stock.view.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StockService {
	
	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private SoldItemRepository soldItemRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	TimeSpanResolver timeSpanResolver;
	

	public StockService() {
		super();
	}
	
	
	@Transactional
	public Stock updateStock(Stock stock) throws OutdatedStockException {
		
		Stock previous  = stockRepository.findByProductId(stock.getProductId());
		
		if (previous!= null ){			
			previous = new Stock (previous);
			stock.setStockId(previous.getStockId());
		}
				
		stock.setTimestamp();		
		stock = stockRepository.save(stock);
		
		
		if (previous!= null && previous.getQuantity() > stock.getQuantity() ){
			
			SoldItem item = new SoldItem();
			item.setProductId(stock.getProductId());
			item.setTimestamp();
			item.setQuantity(previous.getQuantity() - stock.getQuantity());
			
			soldItemRepository.save(item);
		}
		
		
		return stock;
	}
	
	public Product getProduct(String productId) throws ProductNotFoundException{
		
		Stock stock = stockRepository.findByProductId(productId);
		
		if (stock == null)
			throw new ProductNotFoundException();
		
		return new Product (stock);		
	}
	
	
	public void deleteAll(){
		
		stockRepository.deleteAll();
		soldItemRepository.deleteAll();
		
		
	}
	
	
	public long soldItemsCount(){
		
		return soldItemRepository.count();
		
		
	}

}
