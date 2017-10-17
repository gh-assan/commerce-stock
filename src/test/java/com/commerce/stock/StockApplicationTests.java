package com.commerce.stock;

import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
	public void shouldUpdatePost() throws Exception {
		
		Stock stock = new Stock();
		
		stock.setId("1234565");
		stock.setProductId("1234565");
		stock.setQuantity(1);
		
		
		String jsonStock = objectMapper.writeValueAsString(stock);
				
		this.mockMvc.perform(post("/updateStock")
							 .contentType(MediaType.APPLICATION_JSON)
							 .content(jsonStock))
				.andExpect(status().isCreated());
		
	}
	
	
	@Test
	public void ShouldUpdateStockWithNullTimastamp() throws Exception {
		
		Stock stock = new Stock();
		
		stock.setId("000002");
		stock.setProductId("product-002");
		stock.setQuantity(100);
		
		String jsonStock = objectMapper.writeValueAsString(stock);
				
		this.mockMvc.perform(post("/updateStock")
							 .contentType(MediaType.APPLICATION_JSON)
							 .content(jsonStock))
				.andExpect(status().isCreated());
		
			
		this.mockMvc.perform(post("/updateStock")
				 .contentType(MediaType.APPLICATION_JSON)
				 .content(jsonStock))
				 .andExpect(status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()));
		
	}
	
	@Test
	public void PostStockMultiUpdateNewTimastamp() throws Exception {
		
		Stock stock = new Stock();
		
		stock.setId("000003");
		stock.setProductId("product-003");
		stock.setQuantity(100);
		
		String jsonStock = objectMapper.writeValueAsString(stock);
				
		ResultActions action = this.mockMvc.perform(post("/updateStock")
							 .contentType(MediaType.APPLICATION_JSON)
							 .content(jsonStock))
				.andExpect(status().isCreated());
		
		
		
		String response = action.andReturn().getResponse().getContentAsString(); 
		
		Stock  stockResponse = objectMapper.readValue(response , Stock.class);
		
		stock.setTimestamp(stockResponse.getTimestamp());
		jsonStock = objectMapper.writeValueAsString(stock);	
		
		this.mockMvc.perform(post("/updateStock")
				 .contentType(MediaType.APPLICATION_JSON)
				 .content(jsonStock))
				 .andExpect(status().isCreated());
		
	}
	
	@Test
	public void ShouldGetStatisticsTest() throws Exception {
						
		this.mockMvc.perform(get("/statistics?time=lastMonth")
							 .contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		this.mockMvc.perform(get("/statistics?time=today")
				 .contentType(MediaType.APPLICATION_JSON))
				 .andExpect(status().isOk());
		
	}
	
	@Test
	public void  shouldGetStockByProductIdTest() throws Exception {
						
		this.mockMvc.perform(get("/stock?productId=p23")
							 .contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
		
		Stock stock = new Stock();
		
		stock.setId("00033");
		stock.setProductId("p23");
		stock.setQuantity(100);
		
		String jsonStock = objectMapper.writeValueAsString(stock);
				
		this.mockMvc.perform(post("/updateStock")
							 .contentType(MediaType.APPLICATION_JSON)
							 .content(jsonStock))
				.andExpect(status().isCreated());
		
		
		this.mockMvc.perform(get("/stock?productId=p23")
				 .contentType(MediaType.APPLICATION_JSON))
				 .andExpect(status().isOk());

	}
	
	@Test
	public void ShouldNotUpdateStockNegativeValueTest() throws Exception {
						
		Stock stock = new Stock();
		
		stock.setId("00033");
		stock.setProductId("p23");
		stock.setQuantity(-100);
		
		String jsonStock = objectMapper.writeValueAsString(stock);
				
		this.mockMvc.perform(post("/updateStock")
							 .contentType(MediaType.APPLICATION_JSON)
							 .content(jsonStock))
				.andExpect(status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()));
		
	}
}
