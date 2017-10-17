package com.commerce.stock.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.commerce.stock.entity.Stock;
import com.commerce.stock.exception.OutdatedStockException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTest {

	
	@Before
	public void setup() {
		stockService.deleteAll();

	}
	
	@Autowired
	private StockService stockService;
	
	
	@Test
	public void SoldItemsTest() throws Exception {
		
		Stock stock1 = new Stock("000011","top11",10);
		
		stock1 = stockService.updateStock(stock1);
		
		assertEquals(0, stockService.soldItemsCount());
		
		stock1.setQuantity(15);				
		stockService.updateStock(stock1);
		
		assertEquals(0,  stockService.soldItemsCount());
				
		stock1.setQuantity(5);				
		stockService.updateStock(stock1);
				
		assertEquals(1, stockService.soldItemsCount());
	
	}
				
	
	
	
	@Test
	public void updateTransactionTest() throws Exception {
		
		
		Stock stock1 = new Stock("000411","tar-01",10);
		
		stock1 = stockService.updateStock(stock1);
				
		stock1.setQuantity(5);
		
		stock1 = stockService.updateStock(stock1);
		
		assertEquals(1, stockService.soldItemsCount());
	
	}
		
}
