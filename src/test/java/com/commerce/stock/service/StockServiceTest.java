package com.commerce.stock.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.commerce.stock.entity.Stock;
import com.commerce.stock.repository.StockRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTest {

	
	
	@Autowired
	private StockService service;
	
	
	@Test
	public void Top3Test() throws Exception {
		
		Stock stock1 = new Stock("000001","top1",10);
		Stock stock2 = new Stock("000002","top2",11);
		Stock stock3 = new Stock("000003","top3",12);
		Stock stock4 = new Stock("000004","top4",13);
	
		
		service.updateStock(stock1);
		service.updateStock(stock2);
		service.updateStock(stock3);
		service.updateStock(stock4);
		
		
		
		service.top3().forEach(s -> { 
			        assertTrue(s.getQuantity()>10);
		} );
		
		
		assertEquals(3, service.top3().size());
	}
}
