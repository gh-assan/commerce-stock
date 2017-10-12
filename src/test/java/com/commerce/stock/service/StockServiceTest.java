package com.commerce.stock.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.commerce.stock.entity.SoldItem;
import com.commerce.stock.entity.Stock;
import com.commerce.stock.repository.SoldItemRepository;
import com.commerce.stock.util.date.Today;
import com.commerce.stock.valueObject.SoldProduct;

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
	
	
//	@Test
//	public void Top3Test() throws Exception {
//		
//		Stock stock1 = new Stock("000001","top1",10);
//		Stock stock2 = new Stock("000002","top2",11);
//		Stock stock3 = new Stock("000003","top3",12);
//		Stock stock4 = new Stock("000004","top4",13);
//	
//		
//		service.updateStock(stock1);
//		service.updateStock(stock2);
//		service.updateStock(stock3);
//		service.updateStock(stock4);
//		
//		
//		
//		service.top3().forEach(s -> { 
//			        assertTrue(s.getQuantity()>10);
//		} );
//		
//		
//		assertEquals(3, service.top3().size());
//	}
	
	@Test
	public void SoldItemsTest() throws Exception {
		
		Stock stock1 = new Stock("000011","top11",10);
		Stock stock2 = new Stock("000011","top11",5);
		
		
		stock2 = service.updateStock(stock1);
		stock2.setQuantity(5);
		
		System.out.println(stock2);
		
		service.updateStock(stock2);
		
		
		assertEquals(1, ( (Collection) repo.findAll()).size());
	
	}
	
	@Test
	public void updateTransactionTest() throws Exception {
		
		
		Stock stock1 = new Stock("000411","tar-01",10);
		
		stock1 = service.updateStock(stock1);
		
		int count = ( (Collection) repo.findAll()).size();
		
		stock1.setQuantity(5);
		
		stock1 = service.updateStock(stock1);
		
		assertEquals(1, ( (Collection) repo.findAll()).size());
	
	}
		
}
