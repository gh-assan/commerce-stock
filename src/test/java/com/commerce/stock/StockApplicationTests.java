package com.commerce.stock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.commerce.stock.entity.Stock;
import com.commerce.stock.repository.StockRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockApplicationTests {
	
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private StockRepository repository;
	
	
	@Autowired
	private DataSource ds;
	
	@Autowired
	ObjectMapper objectMapper;
	
	
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
		
		assertNotEquals(0, repository.count());
	}
	
	@Test
	public void PostStockUpdate() throws Exception {
		
		Stock stock = new Stock();
		stock.setId("1234565");
		
		String jsonStock = objectMapper.writeValueAsString(stock);
				
		this.mockMvc.perform(post("/updateStock")
							 .contentType(MediaType.APPLICATION_JSON)
							 .content(jsonStock))
				.andExpect(status().isCreated());
		
	}

}
