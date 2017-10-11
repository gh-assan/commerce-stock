package com.commerce.stock.valueObject;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import com.commerce.stock.entity.Stock;
import com.commerce.stock.util.JsonProductSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = JsonProductSerializer.class)
public class Product {
	
	private Date requestTimestamp;
	private Stock stock;
	
	
	public Product(Stock stock) {
		this.stock = stock;
		ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
		this.requestTimestamp =  Date.from(utc.toInstant());
	}


	public Date getRequestTimestamp() {
		return requestTimestamp;
	}


	public Stock getStock() {
		return stock;
	}
	

}
