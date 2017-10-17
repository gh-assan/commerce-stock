package com.commerce.stock.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.commerce.stock.entity.Stock;
import com.commerce.stock.exception.OutdatedStockException;
import com.commerce.stock.exception.ProductNotFoundException;
import com.commerce.stock.service.StatisticsService;
import com.commerce.stock.service.StockService;
import com.commerce.stock.validation.UpdateStockValidator;
import com.commerce.stock.view.Product;
import com.commerce.stock.view.StockStatistics;


@RestController
public class StockController {
	
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private StatisticsService statisticsService;
	
	
	@Autowired
	private UpdateStockValidator updateStockValidator;
	
	
	@InitBinder("stock")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(updateStockValidator);
    }
	
	
	@RequestMapping(value = "/stock",
            		method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	public
    Product getProduct(@RequestParam(value ="productId", required = true) String productId) throws ProductNotFoundException  
	{
		return this.stockService.getProduct(productId);
    }
	
	@RequestMapping(value = "/updateStock",
            		method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public
    Stock updateStock(@Validated @RequestBody Stock stock) throws OutdatedStockException  
	{		
		return  this.stockService.updateStock(stock);
    }
	
	
	@RequestMapping(value = "/statistics",
            		method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	public
    StockStatistics statistics(@RequestParam(value ="time", required = true) String time) throws Exception  
	{
		return statisticsService.stockStatistics(time);
    }
	

}
