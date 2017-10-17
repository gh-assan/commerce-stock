package com.commerce.stock.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

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
import com.commerce.stock.valueObject.StockStatistics;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsServiceTest {

	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private StatisticsService statisticsService;
	
	
	@Autowired
	private SoldItemRepository soldItemRepository;
	
	@Autowired
	private Today today;
	
	@Before
	public void setup() {
		stockService.deleteAll();
	}
	
	
	
	@Test
	public void Top3Test() throws Exception {
		
		Stock stock1 = new Stock("000001","top1",10);
		Stock stock2 = new Stock("000002","top2",11);
		Stock stock3 = new Stock("000003","top3",12);
		Stock stock4 = new Stock("000004","top4",13);
	
		
		stockService.updateStock(stock1);
		stockService.updateStock(stock2);
		stockService.updateStock(stock3);
		stockService.updateStock(stock4);
		
		
		
		statisticsService.top3().forEach(s -> { 
			        assertTrue(s.getQuantity()>10);
		} );
		
		
		assertEquals(3, statisticsService.top3().size());
	}
	
	
	@Test
	public void TopSoldItemsTest() throws Exception {
		
		SoldItem item1 = new SoldItem("p1" , 1);
		SoldItem item11 = new SoldItem("p1" , 2);
		
		SoldItem item2 = new SoldItem("p2" , 2);
		SoldItem item3 = new SoldItem("p3" , 3);
		SoldItem item4 = new SoldItem("p4" , 4);
		SoldItem item5 = new SoldItem("p5" , 5);
		
		soldItemRepository.save(item1);
		soldItemRepository.save(item11);
		soldItemRepository.save(item2);
		soldItemRepository.save(item3);
		soldItemRepository.save(item4);
		soldItemRepository.save(item5);
		
		
		List<SoldProduct> l = statisticsService.top3SoldProducts(today.get().getFrom(), today.get().getUntil());
		
		assertEquals(3,  l.size());
		
		l.forEach(i -> { 
	        assertTrue(i.getQuantity() > 2);			
		} );

		
	}
	
	
	@Test
	public void StatTest() throws Exception {
		
		Stock stock1 = new Stock("000001","top1",100);
		Stock stock2 = new Stock("000002","top2",100);
		Stock stock3 = new Stock("000003","top3",100);
		Stock stock4 = new Stock("000004","top4",100);
	
		
		stock1 = stockService.updateStock(stock1);
		stock2 = stockService.updateStock(stock2);
		stock3 = stockService.updateStock(stock3);
		stock4 = stockService.updateStock(stock4);
		
		stock1.setQuantity(90);
		stock2.setQuantity(80);
		stock3.setQuantity(70);
		stock4.setQuantity(60);
		
		stock1 = stockService.updateStock(stock1);
		stock2 = stockService.updateStock(stock2);
		stock3 = stockService.updateStock(stock3);
		stock4 = stockService.updateStock(stock4);
		
		
		StockStatistics s = statisticsService.stockStatistics("today");
		
		System.out.println(s);
		
		assertNotEquals(null, s);
		
		assertEquals(3, s.getTopAvailableProducts().size());
		assertEquals(3, s.getTopSellingProducts().size());
		
		assertEquals(90, s.getTopAvailableProducts().get(0).getQuantity().intValue());
		assertEquals(40, s.getTopSellingProducts().get(0).getQuantity());
	}
	
}
