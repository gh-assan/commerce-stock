package com.commerce.stock.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commerce.stock.entity.Stock;
import com.commerce.stock.repository.SoldItemRepository;
import com.commerce.stock.repository.StockRepository;
import com.commerce.stock.util.date.TimeSpanResolver;
import com.commerce.stock.valueObject.SoldProduct;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StatisticsService {
	
	@Autowired
	private StockRepository repo;
	
	@Autowired
	private SoldItemRepository itemRepo;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	TimeSpanResolver timeSpanResolver;
	
	public List<Stock> top3(){
		
		return repo.findTop3ByOrderByQuantityDesc();
	}
	
	public List<SoldProduct> top3SoldProducts(Date from, Date to){
		
		 List<Object[]> sold = itemRepo.top3SoldProducts(from, to);
		 
		 List<SoldProduct> result = new ArrayList<SoldProduct>();
		 
		 for (Object[] soldItem : sold) {
			 
			 result.add(new SoldProduct(soldItem[0].toString() , Integer.parseInt(soldItem[1].toString() ) ) );
		 }
		
		return result;
	}
	
}
