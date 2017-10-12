package com.commerce.stock.valueObject;

import java.util.Date;
import java.util.List;

import com.commerce.stock.entity.Stock;

public class StockStatistics {
	
	private Date requestTimestamp;
	private String range;
	private List<Stock> topAvailableProducts ; 
	private List<SoldProduct> topSellingProducts;
	
	
	public StockStatistics(Date requestTimestamp, String range, List<Stock> topAvailableProducts,
			List<SoldProduct> topSellingProducts) {
		super();
		this.requestTimestamp = requestTimestamp;
		this.range = range;
		this.topAvailableProducts = topAvailableProducts;
		this.topSellingProducts = topSellingProducts;
	}
	
	
	public Date getRequestTimestamp() {
		return requestTimestamp;
	}
	public String getRange() {
		return range;
	}
	public List<Stock> getTopAvailableProducts() {
		return topAvailableProducts;
	}
	public List<SoldProduct> getTopSellingProducts() {
		return topSellingProducts;
	}
	
	

}
