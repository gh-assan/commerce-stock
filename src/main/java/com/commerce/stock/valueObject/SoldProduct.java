package com.commerce.stock.valueObject;

public class SoldProduct {
	
	private String productId;
	private int quantity;
	
	
	public SoldProduct(String productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	
	public String getProductId() {
		return productId;
	}
	public int getQuantity() {
		return quantity;
	}
	
	
}
