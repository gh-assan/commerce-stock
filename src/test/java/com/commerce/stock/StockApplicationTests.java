package com.commerce.stock;

import static org.junit.Assert.assertEquals;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.commerce.stock.repository.StockRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockApplicationTests {
	
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private StockRepository repository;
	
	
	@Autowired
	private DataSource ds;
	
	
	private MockMvc mockMvc;
	
	private TestRestTemplate restTemplate = new TestRestTemplate();
	private static boolean loadDataFixtures = true;
	
	@Before
	public void setupMockMvc() {
	mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	
	@Before
	public void loadDataFixtures() {
		if (loadDataFixtures) {
				ResourceDatabasePopulator populator = new ResourceDatabasePopulator(context.getResource("classpath:/test-data.sql"));
				DatabasePopulatorUtils.execute(populator, ds);
				loadDataFixtures = false;
		}
	}
	

	@Test
	public void contextLoads() {
		
		assertEquals(1, repository.count());
	}

}
