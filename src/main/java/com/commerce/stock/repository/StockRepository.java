package com.commerce.stock.repository;

import org.springframework.data.repository.CrudRepository;

import com.commerce.stock.entity.Stock;

public interface StockRepository extends CrudRepository<Stock, String>{

}
