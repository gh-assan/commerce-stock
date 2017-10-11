package com.commerce.stock.api;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.commerce.stock.entity.Stock;
import com.commerce.stock.exception.OutdatedStockException;
import com.commerce.stock.repository.StockRepository;
import com.commerce.stock.service.StockService;


@RestController
public class StockController {
	
	@Autowired
	private StockRepository repo;
	
	@Autowired
	private StockService service;
	
	
	@RequestMapping(value = "/stock",
            method = RequestMethod.GET,
            		produces = MediaType.APPLICATION_JSON_VALUE
            		)
    @ResponseStatus(HttpStatus.OK)
	public
    Stock getStock(@RequestParam("productId") String productId)  
	{
		return this.repo.findByProductId(productId);
    }
	
	@RequestMapping(value = "/updateStock",
            method = RequestMethod.POST,
            		produces = MediaType.APPLICATION_JSON_VALUE
            		)
    @ResponseStatus(HttpStatus.CREATED)
    public
    Stock updateStock(@RequestBody Stock stock) throws OutdatedStockException  
	{
		//throw new OutdatedStockException();
		
		//return this.repo.save(stock);
		
		return  this.service.updateStock(stock);
    }
	
	
	@RequestMapping(value = "/statistics",
            method = RequestMethod.GET,
            		produces = MediaType.APPLICATION_JSON_VALUE
            		)
    @ResponseStatus(HttpStatus.OK)
	public
    Stock statistics(@RequestParam("time") String time)  
	{
		return this.repo.findByProductId(time);
    }
	

	@ExceptionHandler
	@ResponseBody
	String handleIllegalArgumentException(OutdatedStockException e, HttpServletResponse response) throws IOException {
	    response.sendError(e.CODE);
	    //response.getWriter().append(e.getMessage());
	    
	    return e.getMessage();
	}

}
