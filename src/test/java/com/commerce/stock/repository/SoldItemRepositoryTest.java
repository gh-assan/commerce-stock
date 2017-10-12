package com.commerce.stock.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.commerce.stock.entity.SoldItem;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SoldItemRepositoryTest {
	
	@Autowired
	private SoldItemRepository repo;
	
	@After
	public void clean() {
		repo.deleteAll();
	}
	
	@Test
	public void SoldItemsTest() throws Exception {
		
		SoldItem item1 = new SoldItem("p1" , 1);
		SoldItem item11 = new SoldItem("p1" , 2);
		
		SoldItem item2 = new SoldItem("p2" , 2);
		SoldItem item3 = new SoldItem("p3" , 3);
		SoldItem item4 = new SoldItem("p4" , 4);
		SoldItem item5 = new SoldItem("p5" , 5);
		
		repo.save(item1);
		repo.save(item11);
		repo.save(item2);
		repo.save(item3);
		repo.save(item4);
		repo.save(item5);
		
		LocalDate today = LocalDate.now().plusDays(1);
		LocalDate yesterday = today.minusDays(1);
		
		List<Object[]> l = repo.top3SoldProducts(java.sql.Date.valueOf(yesterday), java.sql.Date.valueOf(today));
		
		assertEquals(3,  l.size());
		
		l.forEach(i -> { 
	        assertTrue(((BigInteger)i[1]).intValue() > 2);			
		} );

		
	}
}
