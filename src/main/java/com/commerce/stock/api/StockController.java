package com.commerce.stock.api;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.commerce.stock.entity.Stock;
import com.commerce.stock.exception.HasCode;
import com.commerce.stock.exception.OutdatedStockException;
import com.commerce.stock.exception.ProductNotFoundException;
import com.commerce.stock.service.StatisticsService;
import com.commerce.stock.service.StockService;
import com.commerce.stock.valueObject.Product;
import com.commerce.stock.valueObject.StockStatistics;


@RestController
public class StockController {
	
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private StatisticsService statisticsService;
	
	
	@RequestMapping(value = "/stock",
            		method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	public
    Product getProduct(@RequestParam("productId") String productId) throws ProductNotFoundException  
	{
		return this.stockService.getProduct(productId);
    }
	
	@RequestMapping(value = "/updateStock",
            		method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public
    Stock updateStock(@RequestBody Stock stock) throws OutdatedStockException  
	{		
		return  this.stockService.updateStock(stock);
    }
	
	
	@RequestMapping(value = "/statistics",
            		method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	public
    StockStatistics statistics(@RequestParam("time") String time) throws Exception  
	{
		return statisticsService.stockStatistics(time);
    }
	

	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<Object> exceptionHandler(Exception e, HttpServletResponse response) throws IOException {
	    
		Map<String,String> responseBody = new HashMap<>();
		 responseBody.put("message",e.getMessage());
	        
		
		if (e instanceof HasCode ) {
			return new ResponseEntity<Object>(responseBody,  ((HasCode) e).getCode() );
		}
		
		return new ResponseEntity<Object>(responseBody, HttpStatus.BAD_REQUEST );
		
	}

}
