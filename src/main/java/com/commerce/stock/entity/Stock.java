package com.commerce.stock.entity;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.commerce.stock.util.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(indexes= {
		@Index(name="Stock_productId_idx" , columnList ="productId"),
		@Index(name="Stock_id_idx" , columnList ="id")
})
public class Stock {
	
	@Id
	@GeneratedValue
	@JsonIgnore
	Long stockId;
	
	@NotBlank
	@NotNull
	@Size(min = 1, max = 50)
	String id;
	
	
	Date timestamp;
	
	@NotBlank
	@NotNull
	@Size(min = 1, max = 50)
	String productId;
	
	@NotNull
	@Min(0)
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
			this.stockId = stock.getStockId();
		}
	}
	
	public Stock() {
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	
}
