package com.commerce.stock.entity;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SoldItem {
	
	@Id
	@GeneratedValue
	long id;
	
	Date timestamp;
	String productId;
	Integer quantity;
	
	
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
	public long getId() {
		return id;
	}
	
	public SoldItem(Date timestamp, String productId, Integer quantity) {
		super();
		this.timestamp = timestamp;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	public SoldItem( String productId, Integer quantity) {
		this.productId = productId;
		this.quantity = quantity;
		this.setTimestamp();
	}
	
	
	public SoldItem() {
	}
	
}
