package com.commerce.stock.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.commerce.stock.entity.Stock;
import com.commerce.stock.repository.StockRepository;
import com.commerce.stock.validation.errors.ErrorCode;

@Component
public class UpdateStockValidator implements Validator{
	
	@Autowired
	private StockRepository stockRepository;
	

	@Override
	public boolean supports(Class<?> clazz) {
		return Stock.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		
		
		
		Stock stock = (Stock) target;
		Stock previous  = stockRepository.findByProductId(stock.getProductId());
		
		if (previous!= null ){
			
			
			
			if ( stock.getTimestamp() == null || previous.getTimestamp().compareTo(stock.getTimestamp()) != 0) {					
				//throw new OutdatedStockException();
				errors.reject(ErrorCode.OUTDATED_STOCK.getCode());				
			}
			
		}
		
	}

}
