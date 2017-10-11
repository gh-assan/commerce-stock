package com.commerce.stock.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.commerce.stock.entity.Stock;

public interface StockRepository extends CrudRepository<Stock, String>{
	
	public Stock findByProductId(String productId);
	
	public List<Stock> findTop3ByOrderByQuantityDesc();

}
