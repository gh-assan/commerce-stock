package com.commerce.stock.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.commerce.stock.entity.Stock;
import com.commerce.stock.exception.OutdatedStockException;
import com.commerce.stock.repository.SoldItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTest {

	
	@Before
	public void setup() {
		service.deleteAll();

	}
	
	@Autowired
	private StockService service;
	
	@Autowired
	private SoldItemRepository repo;
	
	
	
	@Test
	public void SoldItemsTest() throws Exception {
		
		Stock stock1 = new Stock("000011","top11",10);
		
		stock1 = service.updateStock(stock1);
		
		assertEquals(0, ( (Collection) repo.findAll()).size());
		
		stock1.setQuantity(15);				
		service.updateStock(stock1);
		
		assertEquals(0, ( (Collection) repo.findAll()).size());
				
		stock1.setQuantity(5);				
		service.updateStock(stock1);
				
		assertEquals(1, ( (Collection) repo.findAll()).size());
	
	}
	
	@Test
	public void InvalidTimeStampTest() throws Exception {
		
		Stock stock1 = new Stock("000011","top11",10);
		Stock stock2 = new Stock("000011","top11",20);
		
		stock1 = service.updateStock(stock1);
		stock2.setTimestamp(stock1.getTimestamp());  
		
		
		stock1.setQuantity(15);				
		stock1 = service.updateStock(stock1);
		
		try {
			service.updateStock(stock2);
			fail( "Update should throw exception" );
		}catch (OutdatedStockException e) {
		}
				
		
	
	}
	
	@Test
	public void updateTransactionTest() throws Exception {
		
		
		Stock stock1 = new Stock("000411","tar-01",10);
		
		stock1 = service.updateStock(stock1);
				
		stock1.setQuantity(5);
		
		stock1 = service.updateStock(stock1);
		
		assertEquals(1, ( (Collection) repo.findAll()).size());
	
	}
		
}
