package com.commerce.stock.entity;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(indexes= {
		@Index(name="SoldItem_productId_idx" , columnList ="productId"),
		@Index(name="SoldItem_timestamp_idx" , columnList ="timestamp")
})
public class SoldItem {
	
	@Id
	@GeneratedValue
	long id;
	
	Date timestamp;
	
	@NotNull
	@NotBlank
	@Size(min = 1, max = 50)
	String productId;
	
	@NotNull
	@Min(0)
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
