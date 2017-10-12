package com.commerce.stock.entity;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.commerce.stock.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Stock {
	
	String id;
	
	Date timestamp;
	
	@Id
	String productId;
	
	Integer quantity;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonSerialize(using=JsonDateSerializer.class) 
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public void setTimestamp() {
		
		ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
		Date timestamp =  Date.from(utc.toInstant());
		
		this.timestamp = timestamp;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", timestamp=" + timestamp + ", productId=" + productId + ", quantity=" + quantity
				+ "]";
	}

	public Stock(String id, String productId, Integer quantity) {
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	public Stock(Stock stock) {
		super();
		
		if (stock != null ) {
			this.id = stock.id;
			this.productId =  stock.productId;
			this.quantity =  stock.quantity;
		}
	}
	
	public Stock() {
	}

	
}
